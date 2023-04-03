package com.example.nettyController.gateway;


import org.springframework.stereotype.Component;

import com.example.nettyController.service.TempService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Gateway {

	private final TempService tempService;
	
	public String branchUrl(String readMessage) {
		log.info("gateway message {}", readMessage);
		// 컨버트를 준다하고.
		
		// 컨버트된 객체 분기
		// 객체 필드값
		switch ( (int)Math.random() ) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;

			//이건 없을거고
		default:
			break;
		}
		
		return "함ㅎ마 asdfasdfasdf\n";
	}
	
	public void convertRequest() {
		
	}
	
}
