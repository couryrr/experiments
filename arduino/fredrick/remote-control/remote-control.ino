const int ch2Pin = 5;
const int ch4Pin = 4;

// Motor setup
const int LEFT_ENGINE = 3;  
const int LEFT_MOTOR = 12;
const int RIGHT_ENGINE = 11;                        
const int RIGHT_MOTOR = 13; 

#define ROLLING_SIZE 10
int left_engine_acceleration;
int right_engine_acceleration;
int rolling_acceleration[ROLLING_SIZE] = {0,0,0,0,0,0,0,0,0,0};
int rolling_direction[ROLLING_SIZE] = {0,0,0,0,0,0,0,0,0,0};

void setup() {  
  // Motor pins
  pinMode(ch2Pin, INPUT); // Acceleration  
  pinMode(ch4Pin, INPUT); // Direction

  pinMode(LEFT_MOTOR, OUTPUT);  
  pinMode(RIGHT_MOTOR, OUTPUT);

  left_engine_acceleration = 0;
  right_engine_acceleration = 0;
  Serial.begin(9600); // Serial Communication is starting with 9600 of baudrate speed
  Serial.println("RC Controls Test");
  Serial.println("with Arduino UNO R3");
}

void loop() {
  
  int ch2 = pulseIn (ch2Pin,HIGH);  // Read and store channel 2
  int ch4 = pulseIn (ch4Pin,HIGH);  // Read and store channel 4  
  
  for(int i=0; i < 10; i++){
    int acc_tmp = rolling_acceleration[i];
    int dir_tmp = rolling_direction[i];
    rolling_acceleration[i] = ch2;
    rolling_direction[i] = ch4;
    ch2 = acc_tmp;
    ch4 = dir_tmp;
  }

  int rolling_acc_sum = 0;
  int rolling_dir_sum = 0;

  for(int i=0; i < 10; i++){
    rolling_acc_sum += rolling_acceleration[i];
    rolling_dir_sum += rolling_direction[i];
  }

  int acceleration = map(rolling_acc_sum/ROLLING_SIZE, 1141, 1778, -255,255);
  int direction    = map(rolling_dir_sum/ROLLING_SIZE, 1101, 1861, -255,255);

  if(acceleration < 0){ // Going backwards
    digitalWrite(LEFT_MOTOR,HIGH);
    digitalWrite(RIGHT_MOTOR,LOW);
  } else {          // Going forwards
    digitalWrite(LEFT_MOTOR,LOW);
    digitalWrite(RIGHT_MOTOR,HIGH);
  } 
  
  left_engine_acceleration = abs(acceleration);
  right_engine_acceleration = abs(acceleration);

  //going right
  if(direction > 0){
    right_engine_acceleration -= direction;
  }

  //going left
  if(direction < 0){
    left_engine_acceleration -= abs(direction);
  }

  Serial.print("rolling_acc_sum:");
  Serial.print(rolling_acc_sum/ROLLING_SIZE);
  Serial.print(",");
  Serial.print("acceleration:");
  Serial.print(acceleration);
  Serial.print(",");
  Serial.print("rolling_dir_sum:");
  Serial.print(rolling_dir_sum/ROLLING_SIZE);
  Serial.print(",");
  Serial.print("direction:");
  Serial.print(direction);
  Serial.print(",");
  Serial.print("left_engine_acceleration:");
  Serial.print(left_engine_acceleration);
  Serial.print(",");
  Serial.print("right_engine_acceleration:");
  Serial.println(right_engine_acceleration);

  //We are between 0 and 255
  if(acceleration <= 255 || acceleration >= 0){
    analogWrite(LEFT_ENGINE, left_engine_acceleration);
    analogWrite(RIGHT_ENGINE, right_engine_acceleration);
  }
}
