package: org.beuwi.msgbost ....

base: 전반적으로 프로그램의 모든 곳에서 사용되는 가장 기본적인 클래스
openapi: 모든 곳에서는 아니지만 대다수의 소스에서 활용되는 클래스

Pane의 경우는 layout에 있는 Pane(getChildren인 경우 layout로 분류)의 경우 그대로 가지만
control에 있는 Pane(getItems인 경우 control로 분류)은 View로 이름을 바꿔서 사용(ScrollPane -> ScrollView)