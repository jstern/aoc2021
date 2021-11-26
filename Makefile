year ?= 2021
JAR := $(CURDIR)/build/libs/aoc2021-1.0.jar

input: $(JAR)
	mkdir -p $(CURDIR)/input/$(year)
	java -cp $(JAR) aoc2021.PuzzleInput $(year) $(day)

solution: $(JAR)
	java -cp $(JAR) aoc2021.Main $(day) $(part)

clean:
	./gradlew clean

build $(JAR):
	./gradlew jar

test:
	./gradlew --rerun-tasks test

start:
	./new_day.py $(day)
	git add src/main/java/day$(day)/Day$(day).java
	git add src/test/java/day$(day)/Day$(day)Tests.java
	git commit -m "started day $(day)"

.PHONY: build input
