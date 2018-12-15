package crypto;

import java.nio.ByteBuffer;


public class Codec {
	
	private ByteBuffer _buf;
	private byte[] _sep;

	public byte[] get_sep() {
		return _sep;
	}

	public static final String Algorithm[] = { "Ed25519", "Secp256k1" };

	public Codec() {
		this._buf = ByteBuffer.allocate(512);
		this._sep = "`".getBytes();

	}

	public byte[] getBytes() {
		int length = _buf.position();
		byte[] bytes = new byte[length];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = _buf.get(i);

		}
		return bytes;
	}

	public void arrayStart() {
		this._buf.put(this._sep);
		this._sep = "^".getBytes();
	}

	public void arrayStartSilent() {
		this._sep = "^".getBytes();
	}

	public void arrayEnd() {
		_sep = "`".getBytes();
	}

	public Codec pushByte(byte n) {
		ByteBuffer bb = ByteBuffer.allocate(2);
		bb.put(n);
		bb = this._escape(bb);
		this._buf.put(this._sep);
		this._buf.put(getBytesFromBufferByte(bb));
		return this;
	}

	public Codec pushInt64(long n) {
		ByteBuffer bb = ByteBuffer.allocate(Long.BYTES);
		bb.putLong(n);
		bb = this._escape(bb);
		this._buf.put(this._sep);
		this._buf.put(getBytesFromBufferByte(bb));
		return this;
	}

	public Codec pushString(String s) {
		ByteBuffer bb = ByteBuffer.allocate(s.length() * 2);
		bb.put(s.getBytes());
		bb = this._escape(bb);
		this._buf.put(this._sep);
		this._buf.put(getBytesFromBufferByte(bb));
		;
		/*
		 * ByteBuffer bb = ByteBuffer.wrap(s.getBytes()); bb = this._escape(bb);
		 * this._buf.put(this._sep); this._buf.put(getBytesFromBufferByte(bb));
		 */
		// pushBytes(s.getBytes(), true);
		return this;
	}

	public Codec pushString(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			pushBytes(strings[i].getBytes(), true);
		}
		return this;
	}

	public void addSeperator() {
		this._buf.put(this._sep);
	}

	public Codec pushBytes(byte[] bytes, boolean isEscape) {
		if (isEscape) {
			for (int i = 0; i < bytes.length; i++) {
				pushByte(bytes[i]);
			}
		} else {
			this._buf.put(this._sep);
			this._buf.put(bytes);
		}

		return this;
	}

	private ByteBuffer _escape(ByteBuffer buf) {
		int length = buf.position();
		/*
		 * If position is zero then it means the buffer is full get the length from
		 * capacity or remaining.
		 */
		if (length < 0) {
			length = buf.remaining();
		}

		ByteBuffer buf2 = ByteBuffer.allocate(length * 2);
		int j = 0;
		byte[] esc = { 92 };
		for (int i = 0; i < length; i++) {
			byte num = buf.get(i);
			switch (num) {
			case 92: // \
			case 94: // ^
			case 96: // `
			case 47: // /
			case 60: // <
				buf2.put(esc);
				break;
			default:
				break;
			}
			buf2.put(num);
		}
		return buf2;
	}

	public byte[] getBytesFromBufferByte(ByteBuffer buffer) {
		int length = buffer.position() - 1;
		if (length <= 0) {
			length = buffer.remaining() - 1;
		}
		byte[] bytes = new byte[length + 1];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = buffer.get(i);
		}
		return bytes;
	}

}