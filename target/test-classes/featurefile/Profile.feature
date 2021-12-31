Feature: Module Name: Profile

@Profile1
Scenario: Verify that the user is able to update the profile and put a comment
  Given Navigate to the URL
  When Enter the given value and click on validate button  
  When Enter valid username in email or channel name
	When Enter valid Password
	When Click on the Login Button
	Then Click on the Settings Icon on your Right Top Corner and Select Edit Profile Option
	And  Read all the Data in the Cover Pic area given
	Then Click on the Dashboard
	Then Click on the Post Section
	Then Select Photo from Local Storage and Click on Post Button
	Then Click on the Comment Option
	Then post the comment
	
	
	


