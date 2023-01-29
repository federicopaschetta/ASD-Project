CC = gcc
all: bin/skip_list bin/Test bin/main_ex2


INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@
	
bin/skip_list: build/skip_list.o build/main_ex2.o $(COMMON_DEPS)
	$(CC) -o bin/skip_list build/skip_list.o build/main_ex2.o

bin/main_ex2: build/main_ex2.o build/skip_list.o  $(COMMON_DEPS)
	$(CC) -o bin/main_ex2 build/main_ex2.o build/skip_list.o

bin/Test: build/skip_list_tests.o build/skip_list.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/skip_list_tests build/skip_list_tests.o build/skip_list.o build/unity.o

clean:
	rm -f build/* bin/*