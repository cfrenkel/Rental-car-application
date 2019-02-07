<?php 
try 
{

	require 'DB_Manage.php';    
	

	    $customerName = $_REQUEST["customerName"];
      	$isOpenOrder = $_REQUEST["isOpenOrder"];
		$numberCar = $_REQUEST["numberCar"];
		$startRental = $_REQUEST["startRental"];
		$endRental = $_REQUEST["endRental"];
      	$kilometerStartValue = $_REQUEST["kilometerStartValue"];
		$kilometerEndValue = $_REQUEST["kilometerEndValue"];
		$isFuelFilled = $_REQUEST["isFuelFilled"];
      	$litersWasFilled = $_REQUEST["litersWasFilled"];
		$orderNumber = $_REQUEST["orderNumber"];

		
		
		$sql = "INSERT INTO `Order`( `customerName`, `isOpenOrder`, `numberCar`, `startRental`,`endRental`,`kilometerStartValue`, `kilometerEndValue`,`isFuelFilled`,`litersWasFilled`,`orderNumber` ) 
		VALUES ('$customerName', '$isOpenOrder', '$numberCar', '$startRental', '$endRental','$kilometerStartValue', '$kilometerEndValue', '$isFuelFilled','$litersWasFilled','$orderNumber')"; 
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
 