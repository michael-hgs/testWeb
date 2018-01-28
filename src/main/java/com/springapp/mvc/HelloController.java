package com.springapp.mvc;

import com.google.appengine.api.ThreadManager;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.List;

@Controller
//@RequestMapping("/")
public class HelloController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "sfdghgkjlk world!");

//		System.out.println(“Sending an iOS push notificatio”);
//		CodeSource codeSource = HelloController.class.getProtectionDomain().getCodeSource();

		String path = this.getClass().getClassLoader().getResource("").getPath();


		String fullPath = null;
		try {
			fullPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pathArr[] = fullPath.split("/target/CheckPush/WEB-INF/classes/");
//		System.out.println(fullPath);
//		System.out.println(pathArr[0]);
		fullPath = pathArr[0];

		File file = new File(pathArr[0] + "/src/main/java/check.p12");
//		/Users/michael/IdeaProjects/CheckPush/src/main/java/check.p12
		///Users/michael/IdeaProjects/CheckPush/main/java/check.p12
//		try {
//			String path = new File(".").getCanonicalPath();
//			System.out.println(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("hello  " + file.exists());

		ApnsService service = APNS.newService()
				.withErrorDetectionThreadFactory(ThreadManager.currentRequestThreadFactory())
				.withCert(pathArr[0] + "/src/main/java/check.p12", "1234")
				.withSandboxDestination()
				.build();
		System.out.println(pathArr.length);
		String payload = APNS.newPayload()
				.alertBody("Cant be simpler than this!").sound("default")
				.alertTitle("test alert title").build();
//
		String token = "f36172e3301f6c5fa35011528f4527b1940af25d7adb9d94286ca87a88dcf6e0";
//
//		System.out.println(“payload: “+payload);
//
		service.push(token, payload);
//
//		System.out.println(“The message has been hopefully sent…”);

		return "hello";
	}


	@RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void listUsers(){
		String path = this.getClass().getClassLoader().getResource("").getPath();


		String fullPath = null;
		try {
			fullPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pathArr[] = fullPath.split("/target/CheckPush/WEB-INF/classes/");
//		System.out.println(fullPath);
//		System.out.println(pathArr[0]);
		fullPath = pathArr[0];

		File file = new File(pathArr[0] + "/src/main/java/check.p12");
//		/Users/michael/IdeaProjects/CheckPush/src/main/java/check.p12
		///Users/michael/IdeaProjects/CheckPush/main/java/check.p12
//		try {
//			String path = new File(".").getCanonicalPath();
//			System.out.println(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("hello  " + file.exists());

		ApnsService service = APNS.newService()
				.withErrorDetectionThreadFactory(ThreadManager.currentRequestThreadFactory())
				.withCert(pathArr[0] + "/src/main/java/check.p12", "1234")
				.withSandboxDestination()
				.build();
		System.out.println(pathArr.length);
		String payload = APNS.newPayload()
				.alertBody("Cant be simpler than this!").sound("default")
				.alertTitle("test alert title").build();
//
		String token = "f36172e3301f6c5fa35011528f4527b1940af25d7adb9d94286ca87a88dcf6e0";
//
//		System.out.println(“payload: “+payload);
//
		service.push(token, payload);
	}



}