<?php 
try 
{
	require 'DB_Manage.php';    
	
	    $userName = $_REQUEST["userName"];
	    $password = $_REQUEST["password"];
		$identificationQuestions = $_REQUEST["identificationQuestions"];
		$ans = $_REQUEST["ans"];
		$sql = "INSERT INTO `User`( `userName`, `password`, `identificationQuestions`, `ans`) 
		VALUES ('$userName', '$password', '$identificationQuestions', '$ans')"; 
		if ($conn->query($sql) === TRUE) 
		{
			$last_id = $conn->insert_id; 
			echo $last_id;  
		}
		else 
		{
			echo "Error: " . $sql . "\n" . $conn->error;  }
			} 
			catch(Exception $e) {  
			echo "Exception Error See Log...."; 
			error_log($e->getMessage() , 0); 
			} 
			$conn->close(); 
?> 