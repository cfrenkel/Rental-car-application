<?php 
try 
{

	require 'DB_Manage.php';    
	
	    $modelCode = $_REQUEST["modelCode"];
      	$companyName = $_REQUEST["companyName"];
		$modalName = $_REQUEST["modalName"];
		$engineCapacity = $_REQUEST["engineCapacity"];
		$gearBoxStatus = $_REQUEST["gearBoxStatus"];
      	$seatingPlace = $_REQUEST["seatingPlace"];
		$productionYear = $_REQUEST["productionYear"];
		
		
		$sql = "INSERT INTO `Model`( `modelCode`, `companyName`, `modalName`, `engineCapacity`,`gearBoxStatus`,`seatingPlace`,`productionYear` ) 
		VALUES ('$modelCode', '$companyName', '$modalName', '$engineCapacity', '$gearBoxStatus','$seatingPlace','$productionYear')"; 
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
 