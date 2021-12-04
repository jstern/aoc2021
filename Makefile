year ?= 2021
JAR := $(CURDIR)/build/libs/aoc2021-1.0.jar

run: $(JAR)
	java --enable-preview -cp $(JAR) aoc2021.Main $(day) $(part)

input: $(JAR)
	mkdir -p $(CURDIR)/input/$(year)
	java --enable-preview -cp $(JAR) aoc2021.PuzzleInput $(year) $(day)

clean:
	./gradlew clean

build $(JAR):
	./gradlew jar

test:
	./gradlew --rerun-tasks test

start: input
	./new_day.py $(day)
	git add src/main/java/aoc2021/day$(day)/Day$(day).java
	git add src/test/java/aoc2021/day$(day)/Day$(day)Tests.java
	git commit -m "started day $(day)"

.PHONY: build input
