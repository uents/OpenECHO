/*
 * Copyright 2012 Sony Computer Science Laboratories, Inc. <info@kadecot.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sonycsl.echo.eoj.device.housingfacilities;

import com.sonycsl.echo.EchoFrame;
import com.sonycsl.echo.eoj.EchoObject;
import com.sonycsl.echo.eoj.device.DeviceObject;

public abstract class Sprinkler extends DeviceObject {
	@SuppressWarnings("unused")
	private static final String TAG = Sprinkler.class.getSimpleName();
	
	public static final byte CLASS_GROUP_CODE = (byte)0x02;
	public static final byte CLASS_CODE = (byte)0x67;

	protected static final byte EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING = (byte)0xE0;
	protected static final byte EPC_SPRINKLE_INTERVAL_SETTING = (byte)0xE1;
	protected static final byte EPC_NUMBER_OF_SPRINKLES_SETTING = (byte)0xE2;
	protected static final byte EPC_SPRINKLE_TIME_SETTING1 = (byte)0xE3;
	protected static final byte EPC_SPRINKLE_TIME_SETTING2 = (byte)0xE4;
	protected static final byte EPC_SPRINKLE_DURATION_SETTING = (byte)0xE5;

	@Override
	public byte getClassGroupCode() {
		return CLASS_GROUP_CODE;
	}

	@Override
	public byte getClassCode() {
		return CLASS_CODE;
	}

	/**
	 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
	 */
	protected abstract boolean setSprinkleValveOpenCloseSetting(byte[] edt);
	/**
	 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
	 */
	protected abstract byte[] getSprinkleValveOpenCloseSetting();
	/**
	 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
	 */
	protected boolean setSprinkleIntervalSetting(byte[] edt) {return false;}
	/**
	 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
	 */
	protected byte[] getSprinkleIntervalSetting() {return null;}
	/**
	 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
	 */
	protected boolean setNumberOfSprinklesSetting(byte[] edt) {return false;}
	/**
	 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
	 */
	protected byte[] getNumberOfSprinklesSetting() {return null;}
	/**
	 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
	 */
	protected boolean setSprinkleTimeSetting1(byte[] edt) {return false;}
	/**
	 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
	 */
	protected byte[] getSprinkleTimeSetting1() {return null;}
	/**
	 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
	 */
	protected boolean setSprinkleTimeSetting2(byte[] edt) {return false;}
	/**
	 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
	 */
	protected byte[] getSprinkleTimeSetting2() {return null;}
	/**
	 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
	 */
	protected boolean setSprinkleDurationSetting(byte[] edt) {return false;}
	/**
	 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
	 */
	protected byte[] getSprinkleDurationSetting() {return null;}


	@Override
	protected void onReceiveSet(EchoFrame res, byte epc, byte pdc, byte[] edt) {
		super.onReceiveSet(res, epc, pdc, edt);
		switch(epc) {
		case EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING:
			res.addProperty(epc, edt, setSprinkleValveOpenCloseSetting(edt));
			break;
		case EPC_SPRINKLE_INTERVAL_SETTING:
			res.addProperty(epc, edt, setSprinkleIntervalSetting(edt));
			break;
		case EPC_NUMBER_OF_SPRINKLES_SETTING:
			res.addProperty(epc, edt, setNumberOfSprinklesSetting(edt));
			break;
		case EPC_SPRINKLE_TIME_SETTING1:
			res.addProperty(epc, edt, setSprinkleTimeSetting1(edt));
			break;
		case EPC_SPRINKLE_TIME_SETTING2:
			res.addProperty(epc, edt, setSprinkleTimeSetting2(edt));
			break;
		case EPC_SPRINKLE_DURATION_SETTING:
			res.addProperty(epc, edt, setSprinkleDurationSetting(edt));
			break;

		}
	}

	@Override
	protected void onReceiveGet(EchoFrame res, byte epc) {
		super.onReceiveGet(res, epc);
		byte[] edt;
		switch(epc) {
		case EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING:
			edt = getSprinkleValveOpenCloseSetting();
			res.addProperty(epc, edt, (edt != null && (edt.length == 1)));
			break;
		case EPC_SPRINKLE_INTERVAL_SETTING:
			edt = getSprinkleIntervalSetting();
			res.addProperty(epc, edt, (edt != null && (edt.length == 1)));
			break;
		case EPC_NUMBER_OF_SPRINKLES_SETTING:
			edt = getNumberOfSprinklesSetting();
			res.addProperty(epc, edt, (edt != null && (edt.length == 1)));
			break;
		case EPC_SPRINKLE_TIME_SETTING1:
			edt = getSprinkleTimeSetting1();
			res.addProperty(epc, edt, (edt != null && (edt.length == 2)));
			break;
		case EPC_SPRINKLE_TIME_SETTING2:
			edt = getSprinkleTimeSetting2();
			res.addProperty(epc, edt, (edt != null && (edt.length == 2)));
			break;
		case EPC_SPRINKLE_DURATION_SETTING:
			edt = getSprinkleDurationSetting();
			res.addProperty(epc, edt, (edt != null && (edt.length == 1)));
			break;

		}
	}
	
	@Override
	public Setter set() {
		return new SetterImpl(ESV_SET_NO_RES);
	}

	@Override
	public Setter setC() {
		return new SetterImpl(ESV_SET_RES);
	}

	@Override
	public Getter get() {
		return new GetterImpl();
	}

	@Override
	public Informer inform() {
		return new InformerImpl();
	}
	
	public static class Receiver extends DeviceObject.Receiver {

		@Override
		protected void onReceiveSetRes(EchoObject eoj, short tid, byte epc,
				byte pdc, byte[] edt) {
			super.onReceiveSetRes(eoj, tid, epc, pdc, edt);
			switch(epc) {
			case EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING:
				onSetSprinkleValveOpenCloseSetting(eoj, tid, (pdc != 0));
				break;
			case EPC_SPRINKLE_INTERVAL_SETTING:
				onSetSprinkleIntervalSetting(eoj, tid, (pdc != 0));
				break;
			case EPC_NUMBER_OF_SPRINKLES_SETTING:
				onSetNumberOfSprinklesSetting(eoj, tid, (pdc != 0));
				break;
			case EPC_SPRINKLE_TIME_SETTING1:
				onSetSprinkleTimeSetting1(eoj, tid, (pdc != 0));
				break;
			case EPC_SPRINKLE_TIME_SETTING2:
				onSetSprinkleTimeSetting2(eoj, tid, (pdc != 0));
				break;
			case EPC_SPRINKLE_DURATION_SETTING:
				onSetSprinkleDurationSetting(eoj, tid, (pdc != 0));
				break;

			}
		}

		@Override
		protected void onReceiveGetRes(EchoObject eoj, short tid, byte epc,
				byte pdc, byte[] edt) {
			super.onReceiveGetRes(eoj, tid, epc, pdc, edt);
			switch(epc) {
			case EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING:
				onGetSprinkleValveOpenCloseSetting(eoj, tid, pdc, edt);
				break;
			case EPC_SPRINKLE_INTERVAL_SETTING:
				onGetSprinkleIntervalSetting(eoj, tid, pdc, edt);
				break;
			case EPC_NUMBER_OF_SPRINKLES_SETTING:
				onGetNumberOfSprinklesSetting(eoj, tid, pdc, edt);
				break;
			case EPC_SPRINKLE_TIME_SETTING1:
				onGetSprinkleTimeSetting1(eoj, tid, pdc, edt);
				break;
			case EPC_SPRINKLE_TIME_SETTING2:
				onGetSprinkleTimeSetting2(eoj, tid, pdc, edt);
				break;
			case EPC_SPRINKLE_DURATION_SETTING:
				onGetSprinkleDurationSetting(eoj, tid, pdc, edt);
				break;

			}
		}
		
		/**
		 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
		 */
		protected void onSetSprinkleValveOpenCloseSetting(EchoObject eoj, short tid, boolean success) {}
		/**
		 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
		 */
		protected void onGetSprinkleValveOpenCloseSetting(EchoObject eoj, short tid, byte pdc, byte[] edt) {}
		/**
		 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
		 */
		protected void onSetSprinkleIntervalSetting(EchoObject eoj, short tid, boolean success) {}
		/**
		 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
		 */
		protected void onGetSprinkleIntervalSetting(EchoObject eoj, short tid, byte pdc, byte[] edt) {}
		/**
		 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
		 */
		protected void onSetNumberOfSprinklesSetting(EchoObject eoj, short tid, boolean success) {}
		/**
		 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
		 */
		protected void onGetNumberOfSprinklesSetting(EchoObject eoj, short tid, byte pdc, byte[] edt) {}
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		protected void onSetSprinkleTimeSetting1(EchoObject eoj, short tid, boolean success) {}
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		protected void onGetSprinkleTimeSetting1(EchoObject eoj, short tid, byte pdc, byte[] edt) {}
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		protected void onSetSprinkleTimeSetting2(EchoObject eoj, short tid, boolean success) {}
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		protected void onGetSprinkleTimeSetting2(EchoObject eoj, short tid, byte pdc, byte[] edt) {}
		/**
		 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
		 */
		protected void onSetSprinkleDurationSetting(EchoObject eoj, short tid, boolean success) {}
		/**
		 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
		 */
		protected void onGetSprinkleDurationSetting(EchoObject eoj, short tid, byte pdc, byte[] edt) {}

	}
	
	public interface Setter extends DeviceObject.Setter {
		public Setter reqSetPower(byte[] edt);
		public Setter reqSetInstallationLocation(byte[] edt);
		public Setter reqSetCurrentLimiting(byte[] edt);
		public Setter reqSetPowerSaving(byte[] edt);
		public Setter reqSetLocation(byte[] edt);
		public Setter reqSetCurrentTime(byte[] edt);
		public Setter reqSetCurrentDate(byte[] edt);
		public Setter reqSetPowerLimitation(byte[] edt);
		
		/**
		 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
		 */
		public Setter reqSetSprinkleValveOpenCloseSetting(byte[] edt);
		/**
		 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
		 */
		public Setter reqSetSprinkleIntervalSetting(byte[] edt);
		/**
		 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
		 */
		public Setter reqSetNumberOfSprinklesSetting(byte[] edt);
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		public Setter reqSetSprinkleTimeSetting1(byte[] edt);
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		public Setter reqSetSprinkleTimeSetting2(byte[] edt);
		/**
		 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
		 */
		public Setter reqSetSprinkleDurationSetting(byte[] edt);

	}

	public class SetterImpl extends DeviceObject.SetterImpl implements Setter {

		public SetterImpl(byte esv) {
			super(esv);
		}
		
		@Override
		public Setter reqSetPower(byte[] edt) {
			return (Setter)super.reqSetPower(edt);
		}

		@Override
		public Setter reqSetInstallationLocation(byte[] edt) {
			return (Setter)super.reqSetInstallationLocation(edt);
		}

		@Override
		public Setter reqSetCurrentLimiting(byte[] edt) {
			return (Setter)super.reqSetCurrentLimiting(edt);
		}

		@Override
		public Setter reqSetPowerSaving(byte[] edt) {
			return (Setter)super.reqSetPowerSaving(edt);
		}

		@Override
		public Setter reqSetLocation(byte[] edt) {
			return (Setter)super.reqSetLocation(edt);
		}

		@Override
		public Setter reqSetCurrentTime(byte[] edt) {
			return (Setter)super.reqSetCurrentTime(edt);
		}

		@Override
		public Setter reqSetCurrentDate(byte[] edt) {
			return (Setter)super.reqSetCurrentDate(edt);
		}

		@Override
		public Setter reqSetPowerLimitation(byte[] edt) {
			return (Setter)super.reqSetPowerLimitation(edt);
		}

		@Override
		public Setter reqSetSprinkleValveOpenCloseSetting(byte[] edt) {
			addProperty(EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING, edt, setSprinkleValveOpenCloseSetting(edt));
			return this;
		}
		@Override
		public Setter reqSetSprinkleIntervalSetting(byte[] edt) {
			addProperty(EPC_SPRINKLE_INTERVAL_SETTING, edt, setSprinkleIntervalSetting(edt));
			return this;
		}
		@Override
		public Setter reqSetNumberOfSprinklesSetting(byte[] edt) {
			addProperty(EPC_NUMBER_OF_SPRINKLES_SETTING, edt, setNumberOfSprinklesSetting(edt));
			return this;
		}
		@Override
		public Setter reqSetSprinkleTimeSetting1(byte[] edt) {
			addProperty(EPC_SPRINKLE_TIME_SETTING1, edt, setSprinkleTimeSetting1(edt));
			return this;
		}
		@Override
		public Setter reqSetSprinkleTimeSetting2(byte[] edt) {
			addProperty(EPC_SPRINKLE_TIME_SETTING2, edt, setSprinkleTimeSetting2(edt));
			return this;
		}
		@Override
		public Setter reqSetSprinkleDurationSetting(byte[] edt) {
			addProperty(EPC_SPRINKLE_DURATION_SETTING, edt, setSprinkleDurationSetting(edt));
			return this;
		}

	}
	
	public class SetterProxy extends DeviceObject.SetterProxy implements Setter {

		public SetterProxy(byte esv) {
			super(esv);
		}

		
		@Override
		public Setter reqSetPower(byte[] edt) {
			return (Setter)super.reqSetPower(edt);
		}

		@Override
		public Setter reqSetInstallationLocation(byte[] edt) {
			return (Setter)super.reqSetInstallationLocation(edt);
		}

		@Override
		public Setter reqSetCurrentLimiting(byte[] edt) {
			return (Setter)super.reqSetCurrentLimiting(edt);
		}

		@Override
		public Setter reqSetPowerSaving(byte[] edt) {
			return (Setter)super.reqSetPowerSaving(edt);
		}

		@Override
		public Setter reqSetLocation(byte[] edt) {
			return (Setter)super.reqSetLocation(edt);
		}

		@Override
		public Setter reqSetCurrentTime(byte[] edt) {
			return (Setter)super.reqSetCurrentTime(edt);
		}

		@Override
		public Setter reqSetCurrentDate(byte[] edt) {
			return (Setter)super.reqSetCurrentDate(edt);
		}

		@Override
		public Setter reqSetPowerLimitation(byte[] edt) {
			return (Setter)super.reqSetPowerLimitation(edt);
		}

		@Override
		public Setter reqSetSprinkleValveOpenCloseSetting(byte[] edt) {
			addProperty(EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Setter reqSetSprinkleIntervalSetting(byte[] edt) {
			addProperty(EPC_SPRINKLE_INTERVAL_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Setter reqSetNumberOfSprinklesSetting(byte[] edt) {
			addProperty(EPC_NUMBER_OF_SPRINKLES_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Setter reqSetSprinkleTimeSetting1(byte[] edt) {
			addProperty(EPC_SPRINKLE_TIME_SETTING1, edt, (edt != null && (edt.length == 2)));
			return this;
		}
		@Override
		public Setter reqSetSprinkleTimeSetting2(byte[] edt) {
			addProperty(EPC_SPRINKLE_TIME_SETTING2, edt, (edt != null && (edt.length == 2)));
			return this;
		}
		@Override
		public Setter reqSetSprinkleDurationSetting(byte[] edt) {
			addProperty(EPC_SPRINKLE_DURATION_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}

	}

	public interface Getter extends DeviceObject.Getter {
		public Getter reqGetPower();
		public Getter reqGetInstallationLocation();
		public Getter reqGetVersion();
		public Getter reqGetIdNumber();
		public Getter reqGetElectricityConsumption();
		public Getter reqGetPowerConsumption();
		public Getter reqGetMakerErrorCode();
		public Getter reqGetCurrentLimiting();
		public Getter reqGetError();
		public Getter reqGetErrorInfo();
		public Getter reqGetMakerCode();
		public Getter reqGetWorkplaceCode();
		public Getter reqGetProductCode();
		public Getter reqGetManufacturingNumber();
		public Getter reqGetDateOfManufacture();
		public Getter reqGetPowerSaving();
		public Getter reqGetLocation();
		public Getter reqGetCurrentTime();
		public Getter reqGetCurrentDate();
		public Getter reqGetPowerLimitation();
		public Getter reqGetWorkingTime();
		public Getter reqGetAnnoPropertyMap();
		public Getter reqGetSetPropertyMap();
		public Getter reqGetGetPropertyMap();
		
		/**
		 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
		 */
		public Getter reqGetSprinkleValveOpenCloseSetting();
		/**
		 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
		 */
		public Getter reqGetSprinkleIntervalSetting();
		/**
		 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
		 */
		public Getter reqGetNumberOfSprinklesSetting();
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		public Getter reqGetSprinkleTimeSetting1();
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		public Getter reqGetSprinkleTimeSetting2();
		/**
		 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
		 */
		public Getter reqGetSprinkleDurationSetting();

	}
	
	public class GetterImpl extends DeviceObject.GetterImpl implements Getter {

		@Override
		public Getter reqGetPower() {
			return (Getter)super.reqGetPower();
		}

		@Override
		public Getter reqGetInstallationLocation() {
			return (Getter)super.reqGetInstallationLocation();
		}

		@Override
		public Getter reqGetVersion() {
			return (Getter)super.reqGetVersion();
		}

		@Override
		public Getter reqGetIdNumber() {
			return (Getter)super.reqGetIdNumber();
		}

		@Override
		public Getter reqGetElectricityConsumption() {
			return (Getter)super.reqGetElectricityConsumption();
		}

		@Override
		public Getter reqGetPowerConsumption() {
			return (Getter)super.reqGetPowerConsumption();
		}

		@Override
		public Getter reqGetMakerErrorCode() {
			return (Getter)super.reqGetMakerErrorCode();
		}

		@Override
		public Getter reqGetCurrentLimiting() {
			return (Getter)super.reqGetCurrentLimiting();
		}

		@Override
		public Getter reqGetError() {
			return (Getter)super.reqGetError();
		}

		@Override
		public Getter reqGetErrorInfo() {
			return (Getter)super.reqGetErrorInfo();
		}

		@Override
		public Getter reqGetMakerCode() {
			return (Getter)super.reqGetMakerCode();
		}

		@Override
		public Getter reqGetWorkplaceCode() {
			return (Getter)super.reqGetWorkplaceCode();
		}

		@Override
		public Getter reqGetProductCode() {
			return (Getter)super.reqGetProductCode();
		}

		@Override
		public Getter reqGetManufacturingNumber() {
			return (Getter)super.reqGetManufacturingNumber();
		}

		@Override
		public Getter reqGetDateOfManufacture() {
			return (Getter)super.reqGetDateOfManufacture();
		}

		@Override
		public Getter reqGetPowerSaving() {
			return (Getter)super.reqGetPowerSaving();
		}

		@Override
		public Getter reqGetLocation() {
			return (Getter)super.reqGetLocation();
		}

		@Override
		public Getter reqGetCurrentTime() {
			return (Getter)super.reqGetCurrentTime();
		}

		@Override
		public Getter reqGetCurrentDate() {
			return (Getter)super.reqGetCurrentDate();
		}

		@Override
		public Getter reqGetPowerLimitation() {
			return (Getter)super.reqGetPowerLimitation();
		}

		@Override
		public Getter reqGetWorkingTime() {
			return (Getter)super.reqGetWorkingTime();
		}

		@Override
		public Getter reqGetAnnoPropertyMap() {
			return (Getter)super.reqGetAnnoPropertyMap();
		}

		@Override
		public Getter reqGetSetPropertyMap() {
			return (Getter)super.reqGetSetPropertyMap();
		}

		@Override
		public Getter reqGetGetPropertyMap() {
			return (Getter)super.reqGetGetPropertyMap();
		}

		@Override
		public Getter reqGetSprinkleValveOpenCloseSetting() {
			byte[] edt = getSprinkleValveOpenCloseSetting();
			addProperty(EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Getter reqGetSprinkleIntervalSetting() {
			byte[] edt = getSprinkleIntervalSetting();
			addProperty(EPC_SPRINKLE_INTERVAL_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Getter reqGetNumberOfSprinklesSetting() {
			byte[] edt = getNumberOfSprinklesSetting();
			addProperty(EPC_NUMBER_OF_SPRINKLES_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Getter reqGetSprinkleTimeSetting1() {
			byte[] edt = getSprinkleTimeSetting1();
			addProperty(EPC_SPRINKLE_TIME_SETTING1, edt, (edt != null && (edt.length == 2)));
			return this;
		}
		@Override
		public Getter reqGetSprinkleTimeSetting2() {
			byte[] edt = getSprinkleTimeSetting2();
			addProperty(EPC_SPRINKLE_TIME_SETTING2, edt, (edt != null && (edt.length == 2)));
			return this;
		}
		@Override
		public Getter reqGetSprinkleDurationSetting() {
			byte[] edt = getSprinkleDurationSetting();
			addProperty(EPC_SPRINKLE_DURATION_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}

	}

	public class GetterProxy extends DeviceObject.GetterProxy implements Getter {

		@Override
		public Getter reqGetPower() {
			return (Getter)super.reqGetPower();
		}

		@Override
		public Getter reqGetInstallationLocation() {
			return (Getter)super.reqGetInstallationLocation();
		}

		@Override
		public Getter reqGetVersion() {
			return (Getter)super.reqGetVersion();
		}

		@Override
		public Getter reqGetIdNumber() {
			return (Getter)super.reqGetIdNumber();
		}

		@Override
		public Getter reqGetElectricityConsumption() {
			return (Getter)super.reqGetElectricityConsumption();
		}

		@Override
		public Getter reqGetPowerConsumption() {
			return (Getter)super.reqGetPowerConsumption();
		}

		@Override
		public Getter reqGetMakerErrorCode() {
			return (Getter)super.reqGetMakerErrorCode();
		}

		@Override
		public Getter reqGetCurrentLimiting() {
			return (Getter)super.reqGetCurrentLimiting();
		}

		@Override
		public Getter reqGetError() {
			return (Getter)super.reqGetError();
		}

		@Override
		public Getter reqGetErrorInfo() {
			return (Getter)super.reqGetErrorInfo();
		}

		@Override
		public Getter reqGetMakerCode() {
			return (Getter)super.reqGetMakerCode();
		}

		@Override
		public Getter reqGetWorkplaceCode() {
			return (Getter)super.reqGetWorkplaceCode();
		}

		@Override
		public Getter reqGetProductCode() {
			return (Getter)super.reqGetProductCode();
		}

		@Override
		public Getter reqGetManufacturingNumber() {
			return (Getter)super.reqGetManufacturingNumber();
		}

		@Override
		public Getter reqGetDateOfManufacture() {
			return (Getter)super.reqGetDateOfManufacture();
		}

		@Override
		public Getter reqGetPowerSaving() {
			return (Getter)super.reqGetPowerSaving();
		}

		@Override
		public Getter reqGetLocation() {
			return (Getter)super.reqGetLocation();
		}

		@Override
		public Getter reqGetCurrentTime() {
			return (Getter)super.reqGetCurrentTime();
		}

		@Override
		public Getter reqGetCurrentDate() {
			return (Getter)super.reqGetCurrentDate();
		}

		@Override
		public Getter reqGetPowerLimitation() {
			return (Getter)super.reqGetPowerLimitation();
		}

		@Override
		public Getter reqGetWorkingTime() {
			return (Getter)super.reqGetWorkingTime();
		}

		@Override
		public Getter reqGetAnnoPropertyMap() {
			return (Getter)super.reqGetAnnoPropertyMap();
		}

		@Override
		public Getter reqGetSetPropertyMap() {
			return (Getter)super.reqGetSetPropertyMap();
		}

		@Override
		public Getter reqGetGetPropertyMap() {
			return (Getter)super.reqGetGetPropertyMap();
		}

		@Override
		public Getter reqGetSprinkleValveOpenCloseSetting() {
			addProperty(EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING);
			return this;
		}
		@Override
		public Getter reqGetSprinkleIntervalSetting() {
			addProperty(EPC_SPRINKLE_INTERVAL_SETTING);
			return this;
		}
		@Override
		public Getter reqGetNumberOfSprinklesSetting() {
			addProperty(EPC_NUMBER_OF_SPRINKLES_SETTING);
			return this;
		}
		@Override
		public Getter reqGetSprinkleTimeSetting1() {
			addProperty(EPC_SPRINKLE_TIME_SETTING1);
			return this;
		}
		@Override
		public Getter reqGetSprinkleTimeSetting2() {
			addProperty(EPC_SPRINKLE_TIME_SETTING2);
			return this;
		}
		@Override
		public Getter reqGetSprinkleDurationSetting() {
			addProperty(EPC_SPRINKLE_DURATION_SETTING);
			return this;
		}

	}
	
	public interface Informer extends DeviceObject.Informer {
		public Informer reqInformPower();
		public Informer reqInformInstallationLocation();
		public Informer reqInformVersion();
		public Informer reqInformIdNumber();
		public Informer reqInformElectricityConsumption();
		public Informer reqInformPowerConsumption();
		public Informer reqInformMakerErrorCode();
		public Informer reqInformCurrentLimiting();
		public Informer reqInformError();
		public Informer reqInformErrorInfo();
		public Informer reqInformMakerCode();
		public Informer reqInformWorkplaceCode();
		public Informer reqInformProductCode();
		public Informer reqInformManufacturingNumber();
		public Informer reqInformDateOfManufacture();
		public Informer reqInformPowerSaving();
		public Informer reqInformLocation();
		public Informer reqInformCurrentTime();
		public Informer reqInformCurrentDate();
		public Informer reqInformPowerLimitation();
		public Informer reqInformWorkingTime();
		public Informer reqInformAnnoPropertyMap();
		public Informer reqInformSetPropertyMap();
		public Informer reqInformGetPropertyMap();
		
		/**
		 * Open/close of sprinkle valve<br>Automatic ON=0x40    manual ON�� 0x41�Cmanual OFF��0x42
		 */
		public Informer reqInformSprinkleValveOpenCloseSetting();
		/**
		 * OFF / daily / every other day / every 3 days / once a week<br>0x40�^0x41�^0x42�^0x43�^0x44
		 */
		public Informer reqInformSprinkleIntervalSetting();
		/**
		 * Number of sprinkles in a day(up to 2 times) First ON�^second ON�^both ON<br>0x41�^0x42�^0x43
		 */
		public Informer reqInformNumberOfSprinklesSetting();
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		public Informer reqInformSprinkleTimeSetting1();
		/**
		 * Set timer value HH:MM and get updated time<br>0~0x17�F0~0x3B (=0~23)�F(=0~59)
		 */
		public Informer reqInformSprinkleTimeSetting2();
		/**
		 * Set timer value MM 0 to 59 minutes<br>0-0x3B (=0~59)
		 */
		public Informer reqInformSprinkleDurationSetting();

	}

	public class InformerImpl extends DeviceObject.InformerImpl implements Informer {

		@Override
		public Informer reqInformPower() {
			return (Informer)super.reqInformPower();
		}

		@Override
		public Informer reqInformInstallationLocation() {
			return (Informer)super.reqInformInstallationLocation();
		}

		@Override
		public Informer reqInformVersion() {
			return (Informer)super.reqInformVersion();
		}

		@Override
		public Informer reqInformIdNumber() {
			return (Informer)super.reqInformIdNumber();
		}

		@Override
		public Informer reqInformElectricityConsumption() {
			return (Informer)super.reqInformElectricityConsumption();
		}

		@Override
		public Informer reqInformPowerConsumption() {
			return (Informer)super.reqInformPowerConsumption();
		}

		@Override
		public Informer reqInformMakerErrorCode() {
			return (Informer)super.reqInformMakerErrorCode();
		}

		@Override
		public Informer reqInformCurrentLimiting() {
			return (Informer)super.reqInformCurrentLimiting();
		}

		@Override
		public Informer reqInformError() {
			return (Informer)super.reqInformError();
		}

		@Override
		public Informer reqInformErrorInfo() {
			return (Informer)super.reqInformErrorInfo();
		}

		@Override
		public Informer reqInformMakerCode() {
			return (Informer)super.reqInformMakerCode();
		}

		@Override
		public Informer reqInformWorkplaceCode() {
			return (Informer)super.reqInformWorkplaceCode();
		}

		@Override
		public Informer reqInformProductCode() {
			return (Informer)super.reqInformProductCode();
		}

		@Override
		public Informer reqInformManufacturingNumber() {
			return (Informer)super.reqInformManufacturingNumber();
		}

		@Override
		public Informer reqInformDateOfManufacture() {
			return (Informer)super.reqInformDateOfManufacture();
		}

		@Override
		public Informer reqInformPowerSaving() {
			return (Informer)super.reqInformPowerSaving();
		}

		@Override
		public Informer reqInformLocation() {
			return (Informer)super.reqInformLocation();
		}

		@Override
		public Informer reqInformCurrentTime() {
			return (Informer)super.reqInformCurrentTime();
		}

		@Override
		public Informer reqInformCurrentDate() {
			return (Informer)super.reqInformCurrentDate();
		}

		@Override
		public Informer reqInformPowerLimitation() {
			return (Informer)super.reqInformPowerLimitation();
		}

		@Override
		public Informer reqInformWorkingTime() {
			return (Informer)super.reqInformWorkingTime();
		}

		@Override
		public Informer reqInformAnnoPropertyMap() {
			return (Informer)super.reqInformAnnoPropertyMap();
		}

		@Override
		public Informer reqInformSetPropertyMap() {
			return (Informer)super.reqInformSetPropertyMap();
		}

		@Override
		public Informer reqInformGetPropertyMap() {
			return (Informer)super.reqInformGetPropertyMap();
		}

		@Override
		public Informer reqInformSprinkleValveOpenCloseSetting() {
			byte[] edt = getSprinkleValveOpenCloseSetting();
			addProperty(EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Informer reqInformSprinkleIntervalSetting() {
			byte[] edt = getSprinkleIntervalSetting();
			addProperty(EPC_SPRINKLE_INTERVAL_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Informer reqInformNumberOfSprinklesSetting() {
			byte[] edt = getNumberOfSprinklesSetting();
			addProperty(EPC_NUMBER_OF_SPRINKLES_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}
		@Override
		public Informer reqInformSprinkleTimeSetting1() {
			byte[] edt = getSprinkleTimeSetting1();
			addProperty(EPC_SPRINKLE_TIME_SETTING1, edt, (edt != null && (edt.length == 2)));
			return this;
		}
		@Override
		public Informer reqInformSprinkleTimeSetting2() {
			byte[] edt = getSprinkleTimeSetting2();
			addProperty(EPC_SPRINKLE_TIME_SETTING2, edt, (edt != null && (edt.length == 2)));
			return this;
		}
		@Override
		public Informer reqInformSprinkleDurationSetting() {
			byte[] edt = getSprinkleDurationSetting();
			addProperty(EPC_SPRINKLE_DURATION_SETTING, edt, (edt != null && (edt.length == 1)));
			return this;
		}

	}
	
	public class InformerProxy extends DeviceObject.InformerProxy implements Informer {

		@Override
		public Informer reqInformPower() {
			return (Informer)super.reqInformPower();
		}

		@Override
		public Informer reqInformInstallationLocation() {
			return (Informer)super.reqInformInstallationLocation();
		}

		@Override
		public Informer reqInformVersion() {
			return (Informer)super.reqInformVersion();
		}

		@Override
		public Informer reqInformIdNumber() {
			return (Informer)super.reqInformIdNumber();
		}

		@Override
		public Informer reqInformElectricityConsumption() {
			return (Informer)super.reqInformElectricityConsumption();
		}

		@Override
		public Informer reqInformPowerConsumption() {
			return (Informer)super.reqInformPowerConsumption();
		}

		@Override
		public Informer reqInformMakerErrorCode() {
			return (Informer)super.reqInformMakerErrorCode();
		}

		@Override
		public Informer reqInformCurrentLimiting() {
			return (Informer)super.reqInformCurrentLimiting();
		}

		@Override
		public Informer reqInformError() {
			return (Informer)super.reqInformError();
		}

		@Override
		public Informer reqInformErrorInfo() {
			return (Informer)super.reqInformErrorInfo();
		}

		@Override
		public Informer reqInformMakerCode() {
			return (Informer)super.reqInformMakerCode();
		}

		@Override
		public Informer reqInformWorkplaceCode() {
			return (Informer)super.reqInformWorkplaceCode();
		}

		@Override
		public Informer reqInformProductCode() {
			return (Informer)super.reqInformProductCode();
		}

		@Override
		public Informer reqInformManufacturingNumber() {
			return (Informer)super.reqInformManufacturingNumber();
		}

		@Override
		public Informer reqInformDateOfManufacture() {
			return (Informer)super.reqInformDateOfManufacture();
		}

		@Override
		public Informer reqInformPowerSaving() {
			return (Informer)super.reqInformPowerSaving();
		}

		@Override
		public Informer reqInformLocation() {
			return (Informer)super.reqInformLocation();
		}

		@Override
		public Informer reqInformCurrentTime() {
			return (Informer)super.reqInformCurrentTime();
		}

		@Override
		public Informer reqInformCurrentDate() {
			return (Informer)super.reqInformCurrentDate();
		}

		@Override
		public Informer reqInformPowerLimitation() {
			return (Informer)super.reqInformPowerLimitation();
		}

		@Override
		public Informer reqInformWorkingTime() {
			return (Informer)super.reqInformWorkingTime();
		}

		@Override
		public Informer reqInformAnnoPropertyMap() {
			return (Informer)super.reqInformAnnoPropertyMap();
		}

		@Override
		public Informer reqInformSetPropertyMap() {
			return (Informer)super.reqInformSetPropertyMap();
		}

		@Override
		public Informer reqInformGetPropertyMap() {
			return (Informer)super.reqInformGetPropertyMap();
		}

		@Override
		public Informer reqInformSprinkleValveOpenCloseSetting() {
			addProperty(EPC_SPRINKLE_VALVE_OPEN_CLOSE_SETTING);
			return this;
		}
		@Override
		public Informer reqInformSprinkleIntervalSetting() {
			addProperty(EPC_SPRINKLE_INTERVAL_SETTING);
			return this;
		}
		@Override
		public Informer reqInformNumberOfSprinklesSetting() {
			addProperty(EPC_NUMBER_OF_SPRINKLES_SETTING);
			return this;
		}
		@Override
		public Informer reqInformSprinkleTimeSetting1() {
			addProperty(EPC_SPRINKLE_TIME_SETTING1);
			return this;
		}
		@Override
		public Informer reqInformSprinkleTimeSetting2() {
			addProperty(EPC_SPRINKLE_TIME_SETTING2);
			return this;
		}
		@Override
		public Informer reqInformSprinkleDurationSetting() {
			addProperty(EPC_SPRINKLE_DURATION_SETTING);
			return this;
		}

	}
}
