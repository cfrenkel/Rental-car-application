<?php 
try 
{

	require 'DB_Manage.php';    
	

	    $lastName = $_REQUEST["lastName"];
      	$firstName = $_REQUEST["firstName"];
		$Id = $_REQUEST["Id"];
		$PhoneNumber = $_REQUEST["PhoneNumber"];
		$emailAddress = $_REQUEST["emailAddress"];
      	$creditCardNumber = $_REQUEST["creditCardNumber"];

		
		
		$sql = "INSERT INTO `Client`( `lastName`, `firstName`, `Id`, `PhoneNumber`,`emailAddress`,`creditCardNumber` ) 
		VALUES ('$lastName', '$firstName', '$Id', '$PhoneNumber', '$emailAddress','$creditCardNumber')"; 
		if ($conn->query($sql) === TRUE) 
		{
			$last_id = $conn->insert_id; 
			echo $last_id;  
		}
		else 
		{
			echo "Error: " . $sql . "\n" . $conn->error; 
			}
			} 
			catch(Exception $e) {  
			echo "Exception Error See Log...."; 
			error_log($e->getMessage() , 0); 
			} 
			$conn->close(); 
?> 
 