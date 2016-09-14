package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	InputStream in;
	
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		
		return in.read();
	}
	
	public int read(byte[] arr) throws IOException {
		
		byte b;
		int size=0;
		while (size<arr.length) {
			
			int count = in.read();
			b =	(byte)in.read();
				
				
			for (int j = 0; j < count; j++) {
				arr[size++] = b;
			}
			
		}
		
		
		return arr.length;	
		}

	}
		