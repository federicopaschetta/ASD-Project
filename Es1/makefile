CC = gcc
all:  bin/Es1 bin/testEs1


INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@


bin/Es1: build/binary_insertion_sort.o build/quicksort.o build/Es1.o $(COMMON_DEPS)
	$(CC) -o bin/Es1 build/binary_insertion_sort.o build/quicksort.o build/Es1.o

bin/testEs1: build/testEs1.o build/quicksort.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/testEs1  build/testEs1.o build/quicksort.o build/unity.o

clean:
	rm -f build/* bin/*