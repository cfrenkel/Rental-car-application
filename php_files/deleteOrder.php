<?php 
try 
{

	require 'DB_Manage.php';    
	

	  
		$orderNumber = $_REQUEST["orderNumber"];

		
		
		$sql = "DELETE FROM Order WHERE orderNumber=$orderNumber";
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
 