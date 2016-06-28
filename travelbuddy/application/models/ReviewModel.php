<?php

Class ReviewModel extends CI_Model
{

		public function getTouristSpot($destination)
		{
			$q="select TOURIST_SPOT_NAME from tourist_spot where DISTRICT_NAME='$destination'";
  			$query=$this->db->query($q);
  			return $query->result_array();
		}


		public function storeReview($spot,$review,$username)
		{
			$q="INSERT INTO `review`(`TOURIST_SPOT_NAME`, `REVIEW`, `USER_NAME`,`DATE_TIME`) VALUES ('$spot','$review','$username',NOW())";
			$query=$this->db->query($q);
		}



}