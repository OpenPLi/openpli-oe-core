OBJ = main.o

.c.o:
	$(CC) -c $(CFLAGS) $< -o $@

subdirboot: $(OBJ)
	$(CC) -static -o $@ $^ $(CFLAGS) $(LDFLAGS)

clean:
	rm -f $(OBJ) subdirboot
