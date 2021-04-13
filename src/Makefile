#Makefile
#Anele Chila
#12 March 2017

JAVAC=/usr/bin/javac

.SUFFIXES: .java .class


.java.class:
	$(JAVAC) $<

classes: Parallel.class ParallelImplement.class Serial.class
default: $(CLASSES)

clean:
	rm *.class
