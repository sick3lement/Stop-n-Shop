<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 $id = (int)$_POST['id'];
 require_once('dbConnect.php');

 $sql = "DELETE FROM cart WHERE id=$id";
 if(mysqli_query($con,$sql)){
 echo 'Successfully removed from Cart';
 }else{
 echo 'oops! Please try again!';
 }
 
}