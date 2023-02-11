package com.TesteChatGPT.ChatGPT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class TesteChatGptApplication {
	private static final String API = "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";

	public static void main(String[] args) {
		SpringApplication.run(TesteChatGptApplication.class, args);
		
		//https://www.youtube.com/watch?v=zpE7fHQaxxE
		
		OpenAiService service = new OpenAiService(API);
		
		CompletionRequest request = CompletionRequest.builder().model("code-davinci-002")
				.prompt("")
				.maxTokens(1000)
				.build();
		
		System.out.println(service.createCompletion(request).getChoices());
		
	}

}
