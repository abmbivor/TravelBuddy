<?php
defined('BASEPATH') OR exit('No direct script access allowed');

Class Api extends CI_Controller{

	public function __construct()
        {
                parent::__construct();
                $this->load->model('LoginModel');
                $this->load->helper('url_helper');
                $this->load->model('RegistrationModel');
                $this->load->model('HomeModel');
                $this->load->model('DestinationDetails');
                $this->load->model('SpotDetails');
                $this->load->model('ReviewModel');
                $this->load->model('ThreadModel');
        }

	public function login()
	{
		$username = $this->input->post('username');
		$password = $this->input->post('password');

		$success= $this->LoginModel->getUserByUsernameAndPassword($username, $password);
		$payload = (object) [
			'success' => $success,
			
		];

		$this->output
	        ->set_content_type('application/json')
	        ->set_output(json_encode($payload));
	}

	public function register()
	{
		$username= $this->input->post('username');
		$email= $this->input->post('email');
		$password= $this->input->post('password');

		$s=$this->LoginModel->getUserByUsernameAndEmail($username, $email);
		if($s==2){$success=2;}
		else if($s==0)
		{
			$this->RegistrationModel->storeUser($username,$email,$password);
			$success=1;
		}
		else {$success=0;}

		$payload=(object)[
				'success'=> $success,

		];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));

	}

	public function home()
	{
		$districts=$this->HomeModel->getDistricts();

		$temp = [];

		foreach ($districts as $district) {
			$temp[] = $district['DISTRICT_NAME'];
		}

		$payload=(object)[
				'districts'=> $temp,

		];
		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));
	}

	public function transport()
	{

		$source= $this->input->get('source');
		$destination= $this->input->get('destination');
		$transport=$this->DestinationDetails->getTransport($source,$destination);

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($transport));
	}

	public function hotel()
	{
		$destination=$this->input->post('destination');
		$hotels=$this->DestinationDetails->getHotel($destination);

		// $temp = [];

		// foreach ($hotels as $hotel) {
		// 	$temp[] = $hotel['HOTEL_NAME'];
		// }
		// $payload=(object)[
		// 		'hotels'=> $temp,

		// ];
		


		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($hotels));
	}

	public function touristSpot()
	{
		$destination=$this->input->get('destination');
		$touristSpot=$this->DestinationDetails->getTouristSpot($destination);

		// $payload=(object)[
		// 		'touristSpot'=> $touristSpot,

		// ];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($touristSpot));
	}
	

	public function spotDescription()
	{
		$spotname=$this->input->get('spotname');
		$spotDescription=$this->SpotDetails->getDescription($spotname);

		$temp = [];

		foreach ($spotDescription as $key) {
			$temp = $key['TEXT'];
		}

		$payload=(object)[
				'TEXT'=> $temp,

		];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));

	}

	public function image()
	{
		$spotname=$this->input->get('spotname');
		$urls=$this->SpotDetails->getUrl($spotname);

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($urls));
	}

	public function spotReview()
	{
		$spotname=$this->input->post('spotname');
		$spotReview=$this->SpotDetails->getReview($spotname);
		// $payload=(object)[
		// 		'spotReview'=> $spotReview,

		// ];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($spotReview));

	}

	public function review_spot()
	{
		$destination=$this->input->get('destination');
		$touristSpots=$this->ReviewModel->getTouristSpot($destination);
		$districts=$this->HomeModel->getDistricts();

		$temp = [];

		foreach ($touristSpots as $touristSpot) {
			$temp[] = $touristSpot['TOURIST_SPOT_NAME'];
		}

		$payload=(object)[
				'districts'=> $temp,

		];
		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));
	}

	public function submitReview()
	{
		$spot=$this->input->post('spot');
		$review=$this->input->post('review');
		$username=$this->input->post('username');

		$this->ReviewModel->storeReview($spot,$review,$username);
		$success=1;
		$payload=(object)[
				'success'=> $success,

		];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));


	}

	public function postThread()
	{
		$text=$this->input->post('text');
		$username=$this->input->post('username');

		$this->ThreadModel->storeThread($text,$username);
		$success=1;
		$payload=(object)[
				'success'=> $success,

		];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));


	}

	public function threadList()
	{
		
		$threadList=$this->ThreadModel->getThreadList();
		

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($threadList));

	}

	public function thread()
	{
		$threadSelected=$this->input->post('thread');
		$thread=$this->ThreadModel->getThread($threadSelected);

		// $this->output
		// 	 ->set_content_type('application/json')
		// 	 ->set_output(json_encode($thread));

		$temp = [];

		foreach ($thread as $key) {
			$temp = $key['QUESTION_TEXT'];
		}

		$temp2 = [];

		foreach ($thread as $key) {
			$temp2 = $key['USER_NAME'];
		}

		$temp3 = [];

		foreach ($thread as $key) {
			$temp3 = $key['DATE_TIME'];
		}

		$payload=(object)[
				'QUESTION_TEXT'=> $temp,
				'USER_NAME'=> $temp2,
				'DATE_TIME' => $temp3,

		];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));

	}

	public function threadDetails()
	{

		$threadSelected=$this->input->post('thread');
		$threadDetails=$this->ThreadModel->getThreadComments($threadSelected);
		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($threadDetails));

	}

	public function postComment()
	{
		$thread=$this->input->post('thread');
		$text=$this->input->post('text');
		$username=$this->input->post('username');

		$this->ThreadModel->postComment($thread,$text,$username);
		$success=1;
		$payload=(object)[
				'success'=> $success,

		];

		$this->output
			 ->set_content_type('application/json')
			 ->set_output(json_encode($payload));	

	}


	
}