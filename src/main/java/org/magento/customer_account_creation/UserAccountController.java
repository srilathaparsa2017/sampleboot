/**
 * 
 */
package org.magento.customer_account_creation;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author rameshparsa
 *
 */
@RestController
public class UserAccountController {
	@Autowired
	private CustomerAccountService2 accountService;

	@PostMapping("/customer/account/create")
	public ResponseEntity<Void> registerStudentForCourse(@RequestBody User newUser) {

		User user = accountService.addUser(newUser);

		if (user == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(user).toUri();

		return ResponseEntity.created(location).build();
	}

}
