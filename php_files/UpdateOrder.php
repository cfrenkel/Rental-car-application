<?php

	require 'DB_Manage.php';    
	

	  
      	$isOpenOrder = $_REQUEST["isOpenOrder"];
		$numberCar = $_REQUEST["numberCar"];
		$endRental = $_REQUEST["endRental"];
		$kilometerEndValue = $_REQUEST["kilometerEndValue"];


$sql = "UPDATE Order SET 
						 isOpenOrder = $isOpenOrder,
						 endRental =   $endRental,
						 kilometerEndValue = $kilometerEndValue
					WHERE numberCar = $numberCar";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>
