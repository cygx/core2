NAMES := \
    $(patsubst core/%.java,%,$(wildcard core/*.java)) \
    $(patsubst core/%.java,%,$(wildcard core/expressions/*.java)) \
    $(patsubst core/%.java,%,$(wildcard core/primitives/*.java)) \
    $(patsubst core/%.java,%,$(wildcard core/statements/*.java)) \

build:
	javac -d classes core/*.java \
		core/expressions/*.java \
		core/primitives/*.java \
		core/statements/*.java

clean:
	rm -rf classes/*

test:
	javac -d classes test.java
	java -cp classes test

$(NAMES):
	javac -d classes core/$@.java
