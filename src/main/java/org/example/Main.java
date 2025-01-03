package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class App {

    // 클래스에서 함수들이 공유해야 하는 변수 -> 인스턴스 변수
    int lastId = 0;
    int wiseSayingSize = 0;
    WiseSaying[] wiseSayingList = new WiseSaying[3];

    public void run() {

        Scanner sc = new Scanner(System.in);
        add("꿈을 지녀라, 그러면 어려운 현실을 이길 수 있다.", "월트 디즈니");

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령: ");
            String command = sc.nextLine();

            if (command.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break;
            } else if (command.equals("등록")) {

                System.out.print("명언 : ");
                String content = sc.nextLine();  // 입력값 가져옴. 입력값이 없으면 기다린다. 엔터를 쳐야 입력이 완료됨. 그래야 넘어감

                System.out.print("작가 : ");
                String author = sc.nextLine();

                add(content, author);   //1. 함수 분리 -> 코드가 줄어든다. 2. 재활용성(중복 제거)

                System.out.println("%d번 명언이 등록되었습니다.".formatted(lastId));
            } else if (command.equals("목록")) {

                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = 0; i < wiseSayingSize; i++) {
                    WiseSaying wiseSaying = wiseSayingList[i];
                    System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
                }

            }
        }
    }

    // 함수 이름 지을 떈 동사
    public void add(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(lastId, content, author);

        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;
        wiseSaying.createDate = LocalDateTime.now();

        wiseSayingList[wiseSayingSize++] = wiseSaying;
    }
}

class WiseSaying {
    int id;
    String content;
    String author;
    LocalDateTime createDate;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}