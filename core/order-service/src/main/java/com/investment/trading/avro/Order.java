/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.investment.trading.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Order extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4785778101767480915L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Order\",\"namespace\":\"com.investment.trading.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"ticket\",\"type\":\"string\"},{\"name\":\"batch\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Order> ENCODER =
      new BinaryMessageEncoder<Order>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Order> DECODER =
      new BinaryMessageDecoder<Order>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Order> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Order> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Order>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Order to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Order from a ByteBuffer. */
  public static Order fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int id;
  @Deprecated public java.lang.CharSequence ticket;
  @Deprecated public int batch;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Order() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param ticket The new value for ticket
   * @param batch The new value for batch
   */
  public Order(java.lang.Integer id, java.lang.CharSequence ticket, java.lang.Integer batch) {
    this.id = id;
    this.ticket = ticket;
    this.batch = batch;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return ticket;
    case 2: return batch;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: ticket = (java.lang.CharSequence)value$; break;
    case 2: batch = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'ticket' field.
   * @return The value of the 'ticket' field.
   */
  public java.lang.CharSequence getTicket() {
    return ticket;
  }

  /**
   * Sets the value of the 'ticket' field.
   * @param value the value to set.
   */
  public void setTicket(java.lang.CharSequence value) {
    this.ticket = value;
  }

  /**
   * Gets the value of the 'batch' field.
   * @return The value of the 'batch' field.
   */
  public java.lang.Integer getBatch() {
    return batch;
  }

  /**
   * Sets the value of the 'batch' field.
   * @param value the value to set.
   */
  public void setBatch(java.lang.Integer value) {
    this.batch = value;
  }

  /**
   * Creates a new Order RecordBuilder.
   * @return A new Order RecordBuilder
   */
  public static com.investment.trading.avro.Order.Builder newBuilder() {
    return new com.investment.trading.avro.Order.Builder();
  }

  /**
   * Creates a new Order RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Order RecordBuilder
   */
  public static com.investment.trading.avro.Order.Builder newBuilder(com.investment.trading.avro.Order.Builder other) {
    return new com.investment.trading.avro.Order.Builder(other);
  }

  /**
   * Creates a new Order RecordBuilder by copying an existing Order instance.
   * @param other The existing instance to copy.
   * @return A new Order RecordBuilder
   */
  public static com.investment.trading.avro.Order.Builder newBuilder(com.investment.trading.avro.Order other) {
    return new com.investment.trading.avro.Order.Builder(other);
  }

  /**
   * RecordBuilder for Order instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Order>
    implements org.apache.avro.data.RecordBuilder<Order> {

    private int id;
    private java.lang.CharSequence ticket;
    private int batch;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.investment.trading.avro.Order.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ticket)) {
        this.ticket = data().deepCopy(fields()[1].schema(), other.ticket);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.batch)) {
        this.batch = data().deepCopy(fields()[2].schema(), other.batch);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Order instance
     * @param other The existing instance to copy.
     */
    private Builder(com.investment.trading.avro.Order other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ticket)) {
        this.ticket = data().deepCopy(fields()[1].schema(), other.ticket);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.batch)) {
        this.batch = data().deepCopy(fields()[2].schema(), other.batch);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.investment.trading.avro.Order.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.investment.trading.avro.Order.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'ticket' field.
      * @return The value.
      */
    public java.lang.CharSequence getTicket() {
      return ticket;
    }

    /**
      * Sets the value of the 'ticket' field.
      * @param value The value of 'ticket'.
      * @return This builder.
      */
    public com.investment.trading.avro.Order.Builder setTicket(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.ticket = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'ticket' field has been set.
      * @return True if the 'ticket' field has been set, false otherwise.
      */
    public boolean hasTicket() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'ticket' field.
      * @return This builder.
      */
    public com.investment.trading.avro.Order.Builder clearTicket() {
      ticket = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'batch' field.
      * @return The value.
      */
    public java.lang.Integer getBatch() {
      return batch;
    }

    /**
      * Sets the value of the 'batch' field.
      * @param value The value of 'batch'.
      * @return This builder.
      */
    public com.investment.trading.avro.Order.Builder setBatch(int value) {
      validate(fields()[2], value);
      this.batch = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'batch' field has been set.
      * @return True if the 'batch' field has been set, false otherwise.
      */
    public boolean hasBatch() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'batch' field.
      * @return This builder.
      */
    public com.investment.trading.avro.Order.Builder clearBatch() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Order build() {
      try {
        Order record = new Order();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.ticket = fieldSetFlags()[1] ? this.ticket : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.batch = fieldSetFlags()[2] ? this.batch : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Order>
    WRITER$ = (org.apache.avro.io.DatumWriter<Order>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Order>
    READER$ = (org.apache.avro.io.DatumReader<Order>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}