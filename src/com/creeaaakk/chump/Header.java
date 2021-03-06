/*
 * Copyright (c) 2013, Creeaaakk Ware
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Creeaaakk Ware nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.creeaaakk.chump;

import java.nio.ByteBuffer;

public class Header
{
  public static final int VERSION_BYTES = Short.SIZE / 8;
  public static final int MESSAGE_TYPE_BYTES = Short.SIZE / 8;
  public static final int TAG_BYTES = Short.SIZE / 8;
  public static final int HEADER_BYTES = VERSION_BYTES + MESSAGE_TYPE_BYTES + TAG_BYTES;

  public final short version;
  public final short messageType;
  public final short tag;

  public Header(short version, short messageType, short tag)
  {
    this.version = version;
    this.messageType = messageType;
    this.tag = tag;
  }

  /**
   * @return byte[] formatted for a message
   */
  public byte[] toBytes()
  {
    byte[] bytes = new byte[calcBytes()];
    ByteBuffer.wrap(bytes).putShort(version).putShort(messageType).putShort(tag);
    return bytes;
  }

  /**
   * @return length of the byte[] that would be returned by toBytes()
   */
  public int calcBytes()
  {
    return HEADER_BYTES;
  }

  /**
   * Helper function to get the version of a header.
   * @param bytes array of bytes with a header at the beginning
   * @return value of the version of the header
   */
  public static short parseVersion(byte[] bytes)
  {
    if (bytes == null)
    {
      throw new IllegalArgumentException("bytes are null");
    }
    else if (bytes.length < HEADER_BYTES)
    {
      throw new IllegalArgumentException("bytes length < " + HEADER_BYTES + ": " + bytes.length);
    }

    return ByteBuffer.wrap(bytes).getShort();
  }

  /**
   * Helper function to get the message type of a header.
   * @param bytes array of bytes with a header at the beginning
   * @return value of the message type of the header
   */
  public static short parseMessageType(byte[] bytes)
  {
    if (bytes == null)
    {
      throw new IllegalArgumentException("bytes are null");
    }
    else if (bytes.length < HEADER_BYTES)
    {
      throw new IllegalArgumentException("bytes length < " + HEADER_BYTES + ": " + bytes.length);
    }

    return ByteBuffer.wrap(bytes).getShort(VERSION_BYTES);
  }

  /**
   * Helper function to get the tag of a header.
   * @param bytes array of bytes with a header at the beginning
   * @return value of the tag of the header
   */
  public static short parseTag(byte[] bytes)
  {
    if (bytes == null)
    {
      throw new IllegalArgumentException("bytes are null");
    }
    else if (bytes.length < HEADER_BYTES)
    {
      throw new IllegalArgumentException("bytes length < " + HEADER_BYTES + ": " + bytes.length);
    }

    return ByteBuffer.wrap(bytes).getShort(VERSION_BYTES + MESSAGE_TYPE_BYTES);
  }
}
