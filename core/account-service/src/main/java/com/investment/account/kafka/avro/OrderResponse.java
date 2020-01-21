/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.investment.account.kafka.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OrderResponse extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5115716074163187066L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderResponse\",\"namespace\":\"com.investment.account.kafka.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"ticket\",\"type\":\"string\"},{\"name\":\"volume\",\"type\":\"string\"},{\"name\":\"condition\",\"type\":\"string\"},{\"name\":\"account\",\"type\":\"string\"},{\"name\":\"bid\",\"type\":\"string\"},{\"name\":\"ask\",\"type\":\"string\"},{\"name\":\"approved\",\"type\":\"boolean\"},{\"name\":\"message\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderResponse> ENCODER =
      new BinaryMessageEncoder<OrderResponse>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderResponse> DECODER =
      new BinaryMessageDecoder<OrderResponse>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<OrderResponse> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<OrderResponse> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderResponse>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this OrderResponse to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a OrderResponse from a ByteBuffer. */
  public static OrderResponse fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int id;
  @Deprecated public java.lang.CharSequence ticket;
  @Deprecated public java.lang.CharSequence volume;
  @Deprecated public java.lang.CharSequence condition;
  @Deprecated public java.lang.CharSequence account;
  @Deprecated public java.lang.CharSequence bid;
  @Deprecated public java.lang.CharSequence ask;
  @Deprecated public boolean approved;
  @Deprecated public java.lang.CharSequence message;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderResponse() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param ticket The new value for ticket
   * @param volume The new value for volume
   * @param condition The new value for condition
   * @param account The new value for account
   * @param bid The new value for bid
   * @param ask The new value for ask
   * @param approved The new value for approved
   * @param message The new value for message
   */
  public OrderResponse(java.lang.Integer id, java.lang.CharSequence ticket, java.lang.CharSequence volume, java.lang.CharSequence condition, java.lang.CharSequence account, java.lang.CharSequence bid, java.lang.CharSequence ask, java.lang.Boolean approved, java.lang.CharSequence message) {
    this.id = id;
    this.ticket = ticket;
    this.volume = volume;
    this.condition = condition;
    this.account = account;
    this.bid = bid;
    this.ask = ask;
    this.approved = approved;
    this.message = message;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return ticket;
    case 2: return volume;
    case 3: return condition;
    case 4: return account;
    case 5: return bid;
    case 6: return ask;
    case 7: return approved;
    case 8: return message;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: ticket = (java.lang.CharSequence)value$; break;
    case 2: volume = (java.lang.CharSequence)value$; break;
    case 3: condition = (java.lang.CharSequence)value$; break;
    case 4: account = (java.lang.CharSequence)value$; break;
    case 5: bid = (java.lang.CharSequence)value$; break;
    case 6: ask = (java.lang.CharSequence)value$; break;
    case 7: approved = (java.lang.Boolean)value$; break;
    case 8: message = (java.lang.CharSequence)value$; break;
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
   * Gets the value of the 'volume' field.
   * @return The value of the 'volume' field.
   */
  public java.lang.CharSequence getVolume() {
    return volume;
  }

  /**
   * Sets the value of the 'volume' field.
   * @param value the value to set.
   */
  public void setVolume(java.lang.CharSequence value) {
    this.volume = value;
  }

  /**
   * Gets the value of the 'condition' field.
   * @return The value of the 'condition' field.
   */
  public java.lang.CharSequence getCondition() {
    return condition;
  }

  /**
   * Sets the value of the 'condition' field.
   * @param value the value to set.
   */
  public void setCondition(java.lang.CharSequence value) {
    this.condition = value;
  }

  /**
   * Gets the value of the 'account' field.
   * @return The value of the 'account' field.
   */
  public java.lang.CharSequence getAccount() {
    return account;
  }

  /**
   * Sets the value of the 'account' field.
   * @param value the value to set.
   */
  public void setAccount(java.lang.CharSequence value) {
    this.account = value;
  }

  /**
   * Gets the value of the 'bid' field.
   * @return The value of the 'bid' field.
   */
  public java.lang.CharSequence getBid() {
    return bid;
  }

  /**
   * Sets the value of the 'bid' field.
   * @param value the value to set.
   */
  public void setBid(java.lang.CharSequence value) {
    this.bid = value;
  }

  /**
   * Gets the value of the 'ask' field.
   * @return The value of the 'ask' field.
   */
  public java.lang.CharSequence getAsk() {
    return ask;
  }

  /**
   * Sets the value of the 'ask' field.
   * @param value the value to set.
   */
  public void setAsk(java.lang.CharSequence value) {
    this.ask = value;
  }

  /**
   * Gets the value of the 'approved' field.
   * @return The value of the 'approved' field.
   */
  public java.lang.Boolean getApproved() {
    return approved;
  }

  /**
   * Sets the value of the 'approved' field.
   * @param value the value to set.
   */
  public void setApproved(java.lang.Boolean value) {
    this.approved = value;
  }

  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public java.lang.CharSequence getMessage() {
    return message;
  }

  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(java.lang.CharSequence value) {
    this.message = value;
  }

  /**
   * Creates a new OrderResponse RecordBuilder.
   * @return A new OrderResponse RecordBuilder
   */
  public static com.investment.account.kafka.avro.OrderResponse.Builder newBuilder() {
    return new com.investment.account.kafka.avro.OrderResponse.Builder();
  }

  /**
   * Creates a new OrderResponse RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderResponse RecordBuilder
   */
  public static com.investment.account.kafka.avro.OrderResponse.Builder newBuilder(com.investment.account.kafka.avro.OrderResponse.Builder other) {
    return new com.investment.account.kafka.avro.OrderResponse.Builder(other);
  }

  /**
   * Creates a new OrderResponse RecordBuilder by copying an existing OrderResponse instance.
   * @param other The existing instance to copy.
   * @return A new OrderResponse RecordBuilder
   */
  public static com.investment.account.kafka.avro.OrderResponse.Builder newBuilder(com.investment.account.kafka.avro.OrderResponse other) {
    return new com.investment.account.kafka.avro.OrderResponse.Builder(other);
  }

  /**
   * RecordBuilder for OrderResponse instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderResponse>
    implements org.apache.avro.data.RecordBuilder<OrderResponse> {

    private int id;
    private java.lang.CharSequence ticket;
    private java.lang.CharSequence volume;
    private java.lang.CharSequence condition;
    private java.lang.CharSequence account;
    private java.lang.CharSequence bid;
    private java.lang.CharSequence ask;
    private boolean approved;
    private java.lang.CharSequence message;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.investment.account.kafka.avro.OrderResponse.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ticket)) {
        this.ticket = data().deepCopy(fields()[1].schema(), other.ticket);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.volume)) {
        this.volume = data().deepCopy(fields()[2].schema(), other.volume);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.condition)) {
        this.condition = data().deepCopy(fields()[3].schema(), other.condition);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.account)) {
        this.account = data().deepCopy(fields()[4].schema(), other.account);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.bid)) {
        this.bid = data().deepCopy(fields()[5].schema(), other.bid);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.ask)) {
        this.ask = data().deepCopy(fields()[6].schema(), other.ask);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.approved)) {
        this.approved = data().deepCopy(fields()[7].schema(), other.approved);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.message)) {
        this.message = data().deepCopy(fields()[8].schema(), other.message);
        fieldSetFlags()[8] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing OrderResponse instance
     * @param other The existing instance to copy.
     */
    private Builder(com.investment.account.kafka.avro.OrderResponse other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ticket)) {
        this.ticket = data().deepCopy(fields()[1].schema(), other.ticket);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.volume)) {
        this.volume = data().deepCopy(fields()[2].schema(), other.volume);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.condition)) {
        this.condition = data().deepCopy(fields()[3].schema(), other.condition);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.account)) {
        this.account = data().deepCopy(fields()[4].schema(), other.account);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.bid)) {
        this.bid = data().deepCopy(fields()[5].schema(), other.bid);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.ask)) {
        this.ask = data().deepCopy(fields()[6].schema(), other.ask);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.approved)) {
        this.approved = data().deepCopy(fields()[7].schema(), other.approved);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.message)) {
        this.message = data().deepCopy(fields()[8].schema(), other.message);
        fieldSetFlags()[8] = true;
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
    public com.investment.account.kafka.avro.OrderResponse.Builder setId(int value) {
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
    public com.investment.account.kafka.avro.OrderResponse.Builder clearId() {
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
    public com.investment.account.kafka.avro.OrderResponse.Builder setTicket(java.lang.CharSequence value) {
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
    public com.investment.account.kafka.avro.OrderResponse.Builder clearTicket() {
      ticket = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'volume' field.
      * @return The value.
      */
    public java.lang.CharSequence getVolume() {
      return volume;
    }

    /**
      * Sets the value of the 'volume' field.
      * @param value The value of 'volume'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setVolume(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.volume = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'volume' field has been set.
      * @return True if the 'volume' field has been set, false otherwise.
      */
    public boolean hasVolume() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'volume' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearVolume() {
      volume = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'condition' field.
      * @return The value.
      */
    public java.lang.CharSequence getCondition() {
      return condition;
    }

    /**
      * Sets the value of the 'condition' field.
      * @param value The value of 'condition'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setCondition(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.condition = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'condition' field has been set.
      * @return True if the 'condition' field has been set, false otherwise.
      */
    public boolean hasCondition() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'condition' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearCondition() {
      condition = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'account' field.
      * @return The value.
      */
    public java.lang.CharSequence getAccount() {
      return account;
    }

    /**
      * Sets the value of the 'account' field.
      * @param value The value of 'account'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setAccount(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.account = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'account' field has been set.
      * @return True if the 'account' field has been set, false otherwise.
      */
    public boolean hasAccount() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'account' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearAccount() {
      account = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'bid' field.
      * @return The value.
      */
    public java.lang.CharSequence getBid() {
      return bid;
    }

    /**
      * Sets the value of the 'bid' field.
      * @param value The value of 'bid'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setBid(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.bid = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'bid' field has been set.
      * @return True if the 'bid' field has been set, false otherwise.
      */
    public boolean hasBid() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'bid' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearBid() {
      bid = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'ask' field.
      * @return The value.
      */
    public java.lang.CharSequence getAsk() {
      return ask;
    }

    /**
      * Sets the value of the 'ask' field.
      * @param value The value of 'ask'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setAsk(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.ask = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'ask' field has been set.
      * @return True if the 'ask' field has been set, false otherwise.
      */
    public boolean hasAsk() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'ask' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearAsk() {
      ask = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'approved' field.
      * @return The value.
      */
    public java.lang.Boolean getApproved() {
      return approved;
    }

    /**
      * Sets the value of the 'approved' field.
      * @param value The value of 'approved'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setApproved(boolean value) {
      validate(fields()[7], value);
      this.approved = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'approved' field has been set.
      * @return True if the 'approved' field has been set, false otherwise.
      */
    public boolean hasApproved() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'approved' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearApproved() {
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public java.lang.CharSequence getMessage() {
      return message;
    }

    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder setMessage(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.message = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public com.investment.account.kafka.avro.OrderResponse.Builder clearMessage() {
      message = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderResponse build() {
      try {
        OrderResponse record = new OrderResponse();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.ticket = fieldSetFlags()[1] ? this.ticket : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.volume = fieldSetFlags()[2] ? this.volume : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.condition = fieldSetFlags()[3] ? this.condition : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.account = fieldSetFlags()[4] ? this.account : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.bid = fieldSetFlags()[5] ? this.bid : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.ask = fieldSetFlags()[6] ? this.ask : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.approved = fieldSetFlags()[7] ? this.approved : (java.lang.Boolean) defaultValue(fields()[7]);
        record.message = fieldSetFlags()[8] ? this.message : (java.lang.CharSequence) defaultValue(fields()[8]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderResponse>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderResponse>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderResponse>
    READER$ = (org.apache.avro.io.DatumReader<OrderResponse>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
