#List of files
C_SRCS = IRSensor.c
C_OBJS = IRSensor.o

#C_HEADERS
OBJS = ${C_OBJS}
EXE = IRSensor

GCC = gcc
GCC_FLAGS = -std=c11 -g -Wall -c -I.

.c.o:
	$(GCC) $(GCC_FLAGS) $<
build: IRSensor
IRSensor: IRSensor.o
	$(GCC) IRSensor.o -o $(EXE)
	
$(C_OBJS): ${C_HEADERS}

clean:
	rm -f *.o *~$(EXE)
package:
	tar -cvzf SecurityProject.tar *.c Makefile