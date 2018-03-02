all: jar

SPIKE:=3
TEST_SUFFIX:=blk

jar: compile
	@cp README.md README.txt
	@jar cvfe spike$(SPIKE).jar Main implementation-notes.txt LICENSE Makefile README.md README.txt sources.txt spike3.txt src/ tests/ -C out .
	@rm README.txt

compile: clean
	@find src/ -name "*.java" > sources.txt
	@mkdir -p out
	@javac -d out @sources.txt

clean: FORCE
	@rm -rf out/* spike$(SPIKE).jar

TEST_FILES:=$(wildcard tests/*.$(TEST_SUFFIX)i)
TEST_RESULTS:=$(patsubst tests/%.$(TEST_SUFFIX)i, tests/%.$(TEST_SUFFIX)o, $(TEST_FILES))

test: jar $(TEST_RESULTS)

tests/%.$(TEST_SUFFIX)o: tests/%.$(TEST_SUFFIX)i FORCE
	@echo -n "[Test $< -> $@ file: "
	@java -jar spike$(SPIKE).jar $< | diff $@ -
	@echo -n "OK, stdin: "
	@cat $< | java -jar spike$(SPIKE).jar | diff $@ -
	@echo "OK]"

.PHONY: FORCE
