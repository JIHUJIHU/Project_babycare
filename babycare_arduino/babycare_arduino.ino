#include <SoftwareSerial.h>                  //블루투스
#include <DHT11.h>                            //온습도센서 쓰기위한 라이브러리
#include <OneWire.h>                           //
#include <DallasTemperature.h>                  //방수온도센서
#define ONE_WIRE_BUS 3                        //방수온도센서 연결 핀
SoftwareSerial btSerial(10,11);             //블루투스 객체 생성
#define TRIG_PIN 7                           //초음파센서 트리거핀
#define ECHO_PIN 8                           //초음파센서 에코핀
OneWire ourWire(ONE_WIRE_BUS);              //
DallasTemperature sensors(&ourWire);        //방수온도센서 객체 생성


int pin =2;                                //온도습도센서 연결 핀
double distance;                              
DHT11 dht11(pin);                         //온도습도센서 객체 생성
String dis="";                            //
String tmp="";    
String hum="";      
String wtmp="";                           //안드로이드에 보내는 프로토콜 정의를 위한 변수
void setup()
{
  Serial.begin(9600);                     //시리얼통신시작과함께 통신속도설정
  btSerial.begin(9600);                   //이하동문
  sensors.begin();                        //방수온도센서통신시작
  pinMode(TRIG_PIN, OUTPUT);            //
  pinMode(ECHO_PIN, INPUT);
}
void loop()
{
  sensors.requestTemperatures();      //방수온습도
  distance = getDistance();           //distance변수에 거리 계산함수결과값을넣음
  Serial.print("distance : ");      
  Serial.println(distance);
  int err;          
  float temp, humi;     
  if((err=dht11.read(humi, temp))==0) // 온습도받아옴
  {
    Serial.print("temperature:");
    Serial.print(temp);
    Serial.print(" humidity:");
    Serial.print(humi);
    Serial.println();
  }
  else
  {
    Serial.println();
    Serial.print("Error No :");
    Serial.print(err);
    Serial.println();    
  }
  sendDis(distance);                      //앞에 #붙힘 - 거리값
  sendhum(humi);                          //앞에 @붙힘 - 일반 습도
  sendTmp(temp);                          //앞에 &붙힘 - 일반 온도
 Serial.println(sensors.getTempCByIndex(0)); sendWtmp(sensors.getTempCByIndex(0));   //앞에 %붙힘 - 방수 온도
  btSerial.print(tmp+" "+hum+" "+dis+" "+wtmp+"\n");//보내는 함수
  delay(DHT11_RETRY_DELAY);
}
double getDistance(){ //거리 계산
  unsigned long duration; 
  float distance; 

  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, LOW);

  duration= pulseIn(ECHO_PIN, HIGH);

  distance=(duration/2)/29.1;

  return distance;
}
void sendDis(float distance){
  dis="";
  dis+=distance;
}
void sendTmp(float temp){
  tmp="";
  tmp+=temp;
}
void sendhum(float humi){
  hum="";
  hum+=humi;
}
void sendWtmp(float temp){
  wtmp="";
  wtmp+=temp;
}
