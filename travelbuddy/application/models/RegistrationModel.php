<?php
defined('BASEPATH') OR exit('No direct script access allowed');

Class RegistrationModel extends CI_Model
{

	public function storeUser($username,$email,$password)
	{
		$q="INSERT INTO `user`(`USER_NAME`, `EMAIL_ID`, `PASSWORD`) VALUES ('$username','$email','$password')";
		$query=$this->db->query($q);
	}


}