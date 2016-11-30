classes: .classes.dummy

tests: .tests.dummy

check: run-tests

clean:; rm -rf classes/* tests/*.class tests/*.tmp .*.dummy
realclean: clean
	rm deps.mk

deps.mk deps:; perl deps.pl >deps.mk

.classes.dummy .tests.dummy: deps.mk

prove prove-v: MAKEFLAGS += --no-print-directory

include deps.mk
