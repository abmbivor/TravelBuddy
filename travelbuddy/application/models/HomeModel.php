<?php
defined('BASEPATH') OR exit('No direct script access allowed');

Class HomeModel extends CI_Model
{

		public function getDistricts()
		{

			$q="select DISTRICT_NAME from district";
  			$query=$this->db->query($q);
  			return $query->result_array();
  		}


}