<?php
defined('BASEPATH') OR exit('No direct script access allowed');

Class LoginModel extends CI_Model
{
		public function getUserByUsernameAndPassword($username, $password)
		{
			$q="select * from user where USER_NAME='$username' and PASSWORD='$password'";
  			$query=$this->db->query($q);
            $count= count($query->result());
            // var_dump($query->result());
            // die();
            // foreach ($query->result() as $key) {
            //          $count=$count+1;
            //     }
          
            if($count>0)return true;
            else return false;
		}

		public function getUserByUsernameAndEmail($username, $email)
		{
			$q="select * from user where USER_NAME='$username' or EMAIL_ID='$email'";
  			$query=$this->db->query($q);
            $count=count($query->result());
           
            if($count>0)return 2;
            else return 0;
		}

}