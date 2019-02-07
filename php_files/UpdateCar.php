<?php

	require 'DB_Manage.php';    
	
	    $carNum = $_REQUEST["carNumber"];
      	$moedlName = $_REQUEST["model"];
		$kilo = $_REQUEST["kilometers"];
		$branchNum = $_REQUEST["branchNumOfParking"];
		

$sql = "UPDATE Order SET carNum = $carNum,
						 moedlName = $moedlName, 
						 kilo = $kilo,
						 branchNum = $branchNum 
					WHERE carNum = $carNum";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>
