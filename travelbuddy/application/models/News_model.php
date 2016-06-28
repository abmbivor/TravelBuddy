<?php
class News_model extends CI_Model {

        
        public function get_news()
		{
  			//$q="select * from user where USER_NAME='$username' and PASSWORD='$password'";
  			//$query=$this->db->query($q);

  			$query = $this->db->get('user');
           // $query=$this->db->select
            return $query->result();
           /* $count=0;
            foreach ($query->result() as $key) {
                     $count=$count+1;
                }
            
            if($count>0)return true;
            else return false;
            //return $query->num_rows;*/
		}
		public function storeUser($username,$email,$password)
	{
		$q="INSERT INTO `user`(`USER_NAME`, `EMAIL_ID`, `PASSWORD`) VALUES ('$username','$email','$password')";
		$query=$this->db->query($q);
	}
}