package com.dd.web;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dd.domain.RequestData;
import com.dd.domain.Visitor;
import com.dd.domain.VisitorFiles;
import com.dd.service.DeviceService;
import com.dd.service.UserService;
import com.dd.service.VisitorFilesService;
import com.dd.service.VisitorService;
import com.dd.util.CognitiveServiceUtil;
import com.dd.util.Firebase;
import com.dd.util.PushFCM;

import lombok.extern.java.Log;

@Log
@Controller
public class NodeController {
	private static final String ATT_ROOT = "D:\\FileUpload\\";
	
	@Autowired
	//private VisitorsFileService vfService;
	private VisitorFilesService vfService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VisitorService visitorService;


	@GetMapping("/resVoice")
	public void resVoice() {
		
	}

	@RequestMapping(value="/notify",
			 method=RequestMethod.POST,
			 produces="application/json;charset=UTF-8")
	public @ResponseBody RequestData notify(@RequestParam("files") MultipartFile[] files, @RequestParam("deviceID") String deviceId) {
		log.info("=========================================");
		log.info("deviceId : "+ deviceId);
		try {
			VisitorFiles vf = new VisitorFiles();
			Integer userNo = userService.getUserNo(deviceId);
			vf.setUserNo(userNo);
			vf.setVisitFileType('a');
			
			for(MultipartFile file:files) {
				log.info("--------------------------------------------------");
				log.info(file.getOriginalFilename());
				log.info(file.getContentType());
				log.info(""+file.getSize());
				
				String contentType = file.getContentType().split("/")[0];
				String uuid = UUID.randomUUID().toString();	//업로드 시 고유한 파일명
				String fileName = uuid + "_" + file.getOriginalFilename();
				
				if(contentType.equals("audio")) {
					vf.setVisitVoiceName(fileName); 
				} else {
					vf.setVisitFileName(fileName);
					vf.setVisitFileType(contentType.charAt(0));
				}
				
				//local에 파일 저장
				OutputStream out = new FileOutputStream(ATT_ROOT + contentType +"\\"+ fileName);
				int saveSuc = FileCopyUtils.copy(file.getInputStream(), out);
				log.info("--------------------------------------------------");
				log.info("저장되고 " + saveSuc);
				
				//방문자 성별,세대 저장
				if(contentType.equals("image")) {
					Visitor visitor = new Visitor();
					visitor.setUserNo(userNo);
					String filePath = ATT_ROOT + contentType +"\\"+ fileName;
					visitor = new CognitiveServiceUtil().cognitiveService(filePath, visitor);
					if (visitor.getAge() != null) {
						visitorService.register(visitor);
					}
				}
			}
			log.info("vf : "+vf);
			
			//방문자 사진/영상 파일정보 DB에 저장
			vf.setVisitFileNo(vfService.register(vf));//저장 후 등록된 글번호 가져오기 
			
			//최근 방문기록 
			List<Float> hours = visitorService.getVisitHour(userNo, 30);
			
			//FCM 발송
			List<String> tokenList = deviceService.getNotiTokenList(userNo);
			
			log.info("tokenList.size :: " + tokenList.size());
			if(tokenList.size() > 0 ) {
				PushFCM.sendPushNotification(tokenList, vf, hours);
			}
			
		} catch(Exception e) {
			log.warning(e.getMessage());
		}
		
		RequestData resObj = new RequestData();
		resObj.setMsg("img success");
		return resObj;
	}
	
	@GetMapping("/displayFile/{mdir}")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(@PathVariable("mdir")String mDir, String name) {
		ResponseEntity<byte[]> result = null;
		HttpHeaders headers = new HttpHeaders();
		log.info("download");
		String fileName = name.substring(37);
		log.info("name : "+name);
		log.info("fileName : " + fileName);
		try {
			InputStream in = new FileInputStream(ATT_ROOT+mDir+"\\"+name);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			IOUtils.copy(in, bos);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "inline; filename=\""+
	          new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			
	        result = new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		return result;
	}

}