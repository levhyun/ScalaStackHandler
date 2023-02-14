import scala.io.StdIn

// 메인 객체
object Main {
  var MAX_SIZE = 128
  var arr = new Array[Int](MAX_SIZE) // 스택 배열
  var top = -1

  // 제목 출력 함수
  def title() { 
    println("[Scala Stack Handler]")
  }

  def commandListPrint() {
    println("---------------------")
    println("| 1. push           |")
    println("| 2. pop            |")
    println("| 3. view           |")
    println("| 4. reset          |")
    println("---------------------")
  }

  def commandSelect(): Int = {
    var commandNumber = 0 // 명령 번호 변수
    var break = 0
    while(break == 0) { // break가 0이 아닐때까지 반복
      commandListPrint() // 명령어 리스트 출력
      printf("Please enter a command number.\n> ")
      commandNumber = StdIn.readInt() // 정수 입력
      break = if (commandNumber > 0 && commandNumber < 5) 1 else 0
      // 조건문이 참이면 break에 1할당, 거짓이면 0 할당
    }
    commandNumber // 명령어 번호 반환
  }

  def valueInput(commandNumber : Int): Int = {
    var value = 0 // 값 변수
    if(commandNumber < 3) { // 명령어 1, 2만 값을 입력받음
      printf("Please enter a value.\n> ")
      var loop = true
      while(loop) {
        value = StdIn.readInt() // 정수 입력
        if(value.getClass.getTypeName == "int"){ // 입력 받은 값의 자료형 확인
          loop = false // 정수일 경우 반복문 종료
        }
      }
    }
    value // 값 반환
  }

  // 삽입 함수
  def push(value : Int) {
    top += 1
    arr(top) = value // 삽입
    println(s"Successfully pushed value ${value} to array")
  }

  // 식제 함수
  def pop(value : Int) {
    var arrTemp = new Array[Int](top) // 임시 배열
    var pre = -1
    for(index <- 0 to top) {
      if(arr(index) != value) { // 입력받은 값이 배열의 값과 다를 경우 참
        pre += 1
        arrTemp(pre) = arr(index) // 배열의 값을 임시 배열에 저장
      }
    }
    // 임시 배열의 값을 원래 배열로 복사
    for(index <- 0 to pre) {
      arr(index) = arrTemp(index)
    }
    top = pre
  }

  // 뷰 함수
  def view() {
    if(top != -1) {
      printf("STACK: (arr)[%d", arr(0))
      for(index <- 1 to top) {
        printf(",%d", arr(index))
      }
      printf("]\ntop : %d\n", top)
    } else {
      printf("STACK: (arr)[empty", arr(0))
      printf("]\ntop : %d\n", top)
    }
  }

  // 초기화 함수
  def reset() {
    top = -1
  }

  // 메인 함수
  def main(args : Array[String]): Unit = {
    while(true) {
      title()
      var commandNumber = commandSelect() // 명령 번호 입력 함수 호출
      var value = valueInput(commandNumber) // 명령어 수행 값 입력 함수 호출

      commandNumber match { // 명령어 번호에 맞는 명령어 함수 호출
        case 1 => push(value) // 삽입
        case 2 => pop(value) // 삭제
        case 3 => view() // 뷰
        case 4 => reset() // 초기화
      }

      println("");
    }
  }
}