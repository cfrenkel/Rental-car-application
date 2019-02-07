<?php 
try 
{
	require 'DB_Manage.php';    
	
	    $address = $_REQUEST["address"];
      	$parkingSpacesNum = $_REQUEST["parkingSpacesNum"];
		$branchNum = $_REQUEST["branchNum"];
		$sql = "INSERT INTO `Branch`( `address`, `parkingSpacesNum`, `branchNum`) 
		VALUES ('$address', '$parkingSpacesNum', '$branchNum')"; 
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