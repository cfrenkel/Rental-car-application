<?php 
try 
{

	require 'DB_Manage.php';    
	

	  
      	$price = $_REQUEST["price"];
		$numberCar = $_REQUEST["numberCar"];
		
		
		$sql = "INSERT INTO `Price`(  `numberCar`, `price` ) 
		VALUES ( '$numberCar','$price')"; 
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
 