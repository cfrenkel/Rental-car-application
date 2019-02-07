<?php 
try 
{
	require 'DB_Manage.php';    
	
	    $carNum = $_REQUEST["carNumber"];
      	$moedlName = $_REQUEST["model"];
		$kilo = $_REQUEST["kilometers"];
		$branchNum = $_REQUEST["branchNumOfParking"];
		$sql = "INSERT INTO `Car`( `carNumber`, `model`, `kilometers`, `branchNumOfParking`) 
		VALUES ('$carNum', '$moedlName', '$kilo', '$branchNum')"; 
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