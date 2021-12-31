Feature: Module Name: Login

@login1
Scenario: Verify that the user is able to login with correct credentials
  Given Navigate to the URL
  When Get current url after navigation and compare with actual given url
  Then Enter the given value and validate error message
  When Enter the given value and click on validate button  
  When Enter valid username in email or channel name
	When Enter valid Password
	When Click on the eye icon to make your password visible
	When Click on the Login Button
	Then Verify that the user is able to login successfully into the system


