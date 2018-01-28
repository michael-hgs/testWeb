package com.springapp.mvc;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");

//		System.out.println(“Sending an iOS push notificatio”);

		ApnsService service = APNS.newService()
				.withCert("/Users/michael/IdeaProjects/CheckPush/src/check.p12", "1234")
				.withSandboxDestination()
				.build();

		String payload = APNS.newPayload()
				.alertBody("Cant be simpler than this!")
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
}