package com.NotificationRequest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.NotificationRequest.dto.ResponseMessage;
import com.NotificationRequest.model.Client;
import com.NotificationRequest.model.ClientRequest;
import com.NotificationRequest.service.ClientInterface;
import com.google.common.util.concurrent.RateLimiter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/notification/")
@ApiModel(description = "x,y,z corporation company endpoint")
public class Controller {
	
	@Autowired
	@Qualifier("normal")
	private RateLimiter normal;

	@Autowired
	@Qualifier("premium")
	private RateLimiter premium;

	@Autowired
	private ClientInterface clientService;
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@ApiOperation(value="Create new client endpoint", 
			notes = "Write client name and category as parameter for new client, category must be premium or normal")
	@PostMapping(value = "createClient")
	public ResponseEntity<?> createClient(@RequestParam("clientName") String clientName,
			@RequestParam("category") String category) {
		try {
			Client client = clientService.saveClient(clientName, category);
			return new ResponseEntity<>(new ResponseMessage("well success saved and Client Id : " + client.getId()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value="Retrieve Client Request endpoint", 
			notes = "Write client identification as parameter")
	@GetMapping("clientRequest")
	public List<ClientRequest> clientRequestList(@RequestParam("clientId") String clientId) {
		return clientService.findClientRateLimite(clientId);
	}

	@ApiOperation(value="Send request endpoint", 
			notes = "For this endpoint you have to write client identification as parameter for sending request")
	@PutMapping("sendRequest")
	public ResponseEntity<?> sendRequest(HttpServletRequest request,@RequestParam("clientId") String clientId) {
		logger.info("ip address is {}",request.getServerPort());
		try {
			boolean check = false;
			String result = clientService.sendRequest(clientId);
			if (result.equals("premium")) {
				System.out.println(result);
				check = premium.tryAcquire();
			}else if(result.equals("normal")){
				System.out.println(result);
				check = normal.tryAcquire();
			}else {
				System.out.println(result);
				return new ResponseEntity<>(new ResponseMessage("REQUESTS YOU ARE ALLOWED PER MONTH HAS ENDED!"),HttpStatus.TOO_MANY_REQUESTS);
			}
			if (!check && result.equals("normal")) {
				System.out.println("waiting and sent");
				normal.acquire();
			}
			if (!check && result.equals("premium")) {
				System.out.println("waiting and sent");
				premium.acquire();
			}
			return new ResponseEntity<>(new ResponseMessage("WELL SENT! "+request.getServerName()),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="renew request", 
			notes = "this endpoint is used to update the client request if he/she had arleady registered and you have to write the client identification"
					+ "and category(premium or normal) as parameters")
	@PutMapping(value = "renewRequest")
	public ResponseEntity<?> renewRequest(@RequestParam("clientId") String clientId, @RequestParam("category")String category) {
		try {
		
			clientService.update(clientId, category);
			return new ResponseEntity<>(new ResponseMessage("well updated"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	

}
