<?php 
try 
{

	require 'DB_Manage.php';    
	

	  
		$carNumber = $_REQUEST["carNumber"];

		
		
		$sql = "DELETE FROM Car WHERE carNumber=$carNumber";
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
 