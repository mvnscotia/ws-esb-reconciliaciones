package com.banorte.ws.esb.reconciliaciones.ws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.banorte.ws.esb.reconciliaciones.service.UserServiceImpl;
import com.banorte.ws.esb.reconciliaciones.entity.User;
import com.banorte.ws.esb.reconciliaciones.schema.*;

@Endpoint
public class UserEndpoint<UserResponse> {

	private static final String NAMESPACE_URI = "http://www.example.org/User";

	@Autowired
	UserServiceImpl userServiceImpl;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userRequest")
	@ResponsePayload
	public JAXBElement<com.banorte.ws.esb.reconciliaciones.schema.UserResponse> getUser(
			@RequestPayload JAXBElement<UserRequest> request) {
		ObjectFactory objectFactory = new ObjectFactory();

		com.banorte.ws.esb.reconciliaciones.schema.UserResponse userResponseFactory = objectFactory
				.createUserResponse();

		JAXBElement<com.banorte.ws.esb.reconciliaciones.schema.UserResponse> userResponse = objectFactory
				.createUserResponse(userResponseFactory);

		try {
			int action = request.getValue().getAction();

			if (action == 1) {
				com.banorte.ws.esb.reconciliaciones.schema.UserResponse userResponseObject = new com.banorte.ws.esb.reconciliaciones.schema.UserResponse();

				List<User> listUser = userServiceImpl.findAll();

				if (listUser != null) {
					for (User us : listUser) {
						UserDetails userDetails = new UserDetails();
						userDetails.setId(us.getId());
						userDetails.setName(us.getName());
						userDetails.setAlias(us.getAlias());
						userResponseObject.getUserDetails().add(userDetails);
					}
				}
				userResponse.setValue(userResponseObject);
			} else if (action == 2) {
				com.banorte.ws.esb.reconciliaciones.schema.UserResponse userResponseObject = new com.banorte.ws.esb.reconciliaciones.schema.UserResponse();

				User user = userServiceImpl.findByID(new Integer(request.getValue().getId()));
				if (user != null) {

					UserDetails userDetails = new UserDetails();
					userDetails.setId(user.getId());
					userDetails.setName(user.getName());
					userDetails.setAlias(user.getAlias());
					userResponseObject.getUserDetails().add(userDetails);
					userResponse.setValue(userResponseObject);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userResponse;

	}
}
