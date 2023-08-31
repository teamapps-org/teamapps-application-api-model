/*-
 * ========================LICENSE_START=================================
 * TeamApps Application API Model
 * ---
 * Copyright (C) 2020 - 2023 TeamApps.org
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
import org.teamapps.universaldb.model.DatabaseModel;
import org.teamapps.universaldb.model.EnumModel;
import org.teamapps.universaldb.model.TableModel;
import org.teamapps.universaldb.schema.*;

import java.util.Arrays;

import static org.teamapps.universaldb.schema.TableOption.*;

public class Model implements ModelProvider {

	@Override
	public DatabaseModel getModel() {
		DatabaseModel model = new DatabaseModel("controlCenter", "Control center", "org.teamapps.model", "controlCenterModel");
		model.setPojoBuildTime(1687083086395L);
		EnumModel geoLocationType = model.createEnum("geoLocationType", "Geo location type", Arrays.asList("country", "state", "city", "place", "none"), Arrays.asList("country", "state", "city", "place", "none"));
		EnumModel orgUnitLifeCycleStatus = model.createEnum("orgUnitLifeCycleStatus", "Org unit life cycle status", Arrays.asList("active", "inactive", "prepareDeletion", "deleted"), Arrays.asList("Active", "Inactive", "Prepare deletion", "Deleted"));

		TableModel addressView = model.createRemoteTable("addressView", "Address view", "address", "controlCenter", "org.teamapps.model");
		TableModel organizationFieldView = model.createRemoteTable("organizationFieldView", "Organization field view", "organizationField", "controlCenter", "org.teamapps.model");
		TableModel organizationUnitTypeView = model.createRemoteTable("organizationUnitTypeView", "Organization unit type view", "organizationUnitType", "controlCenter", "org.teamapps.model");
		TableModel organizationUnitView = model.createRemoteTable("organizationUnitView", "Organization unit view", "organizationUnit", "controlCenter", "org.teamapps.model");
		TableModel userView = model.createRemoteTable("userView", "User view", "user", "controlCenter", "org.teamapps.model");

		addressView.addText("name", "name");
		addressView.addText("organisation", "organisation");
		addressView.addText("street", "street");
		addressView.addText("city", "city");
		addressView.addText("dependentLocality", "Dependent locality");
		addressView.addText("state", "state");
		addressView.addText("postalCode", "Postal code");
		addressView.addText("country", "country");
		addressView.addFloat("latitude", "latitude");
		addressView.addFloat("longitude", "longitude");

		organizationFieldView.addTranslatableText("title", "title");
		organizationFieldView.addText("icon", "icon");

		organizationUnitTypeView.addTranslatableText("name", "name");
		organizationUnitTypeView.addTranslatableText("abbreviation", "abbreviation");
		organizationUnitTypeView.addText("icon", "icon");
		organizationUnitTypeView.addBoolean("translateOrganizationUnits", "Translate organization units");
		organizationUnitTypeView.addBoolean("allowUserContainer", "Allow user container");
		organizationUnitTypeView.addReference("defaultChildType", "Default child type", organizationUnitTypeView, false);
		organizationUnitTypeView.addMultiReference("possibleChildrenTypes", "Possible children types", organizationUnitTypeView, false);
		organizationUnitTypeView.addEnum("geoLocationType", "Geo location type", geoLocationType);

		organizationUnitView.addTranslatableText("name", "name");
		organizationUnitView.addReference("parent", "parent", organizationUnitView, false);
		organizationUnitView.addMultiReference("children", "children", organizationUnitView, false);
		organizationUnitView.addReference("type", "type", organizationUnitTypeView, false);
		organizationUnitView.addText("icon", "icon");
		organizationUnitView.addEnum("orgUnitLifeCycleStatus", "Org unit life cycle status", orgUnitLifeCycleStatus);
		organizationUnitView.addReference("address", "address", addressView, false);

		userView.addText("firstName", "First name");
		userView.addText("firstNameTranslated", "First name translated");
		userView.addText("lastName", "Last name");
		userView.addText("lastNameTranslated", "Last name translated");
		userView.addByteArray("profilePicture", "Profile picture");
		userView.addByteArray("profilePictureLarge", "Profile picture large");
		userView.addText("language", "language");
		userView.addReference("organizationUnit", "Organization unit", organizationUnitView, false);


		return model;
	}

}
