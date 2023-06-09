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
import org.teamapps.universaldb.model.TableModel;
import org.teamapps.universaldb.schema.*;

import java.util.Arrays;

import static org.teamapps.universaldb.schema.TableOption.*;

public class Model implements ModelProvider {

	@Override
	public DatabaseModel getModel() {
		DatabaseModel model = new DatabaseModel("controlCenter", "Control center", "org.teamapps.model");
		model.setPojoBuildTime(1686231909478L);
		model.createEnum("orgUnitLifeCycleStatus", "Org unit life cycle status", Arrays.asList("active", "inactive", "prepareDeletion"), Arrays.asList("active", "inactive", "Prepare deletion"));
		model.createEnum("geoLocationType", "Geo location type", Arrays.asList("country", "state", "city", "place", "none"), Arrays.asList("country", "state", "city", "place", "none"));

		TableModel organizationUnitViewTable = model.createRemoteTable("organizationUnitView", "Organization unit view", "controlCenter");
		TableModel organizationUnitTypeViewTable = model.createRemoteTable("organizationUnitTypeView", "Organization unit type view", "controlCenter");
		TableModel organizationFieldViewTable = model.createRemoteTable("organizationFieldView", "Organization field view", "controlCenter");
		TableModel addressViewTable = model.createRemoteTable("addressView", "Address view", "controlCenter");
		TableModel userViewTable = model.createRemoteTable("userView", "User view", "controlCenter");

		organizationUnitViewTable.addTranslatableText("name", "name");
		organizationUnitViewTable.addReference("parent", "parent", "organizationUnitView", false);
		organizationUnitViewTable.addMultiReference("children", "children", "organizationUnitView", false);
		organizationUnitViewTable.addReference("type", "type", "organizationUnitTypeView", false);
		organizationUnitViewTable.addText("icon", "icon");
		organizationUnitViewTable.addEnum("orgUnitLifeCycleStatus", "Org unit life cycle status", "orgUnitLifeCycleStatus");
		organizationUnitViewTable.addReference("address", "address", "addressView", false);

		organizationUnitTypeViewTable.addTranslatableText("name", "name");
		organizationUnitTypeViewTable.addTranslatableText("abbreviation", "abbreviation");
		organizationUnitTypeViewTable.addText("icon", "icon");
		organizationUnitTypeViewTable.addBoolean("translateOrganizationUnits", "Translate organization units");
		organizationUnitTypeViewTable.addBoolean("allowUserContainer", "Allow user container");
		organizationUnitTypeViewTable.addReference("defaultChildType", "Default child type", "organizationUnitTypeView", false);
		organizationUnitTypeViewTable.addMultiReference("possibleChildrenTypes", "Possible children types", "organizationUnitTypeView", false);
		organizationUnitTypeViewTable.addEnum("geoLocationType", "Geo location type", "geoLocationType");

		organizationFieldViewTable.addTranslatableText("title", "title");
		organizationFieldViewTable.addText("icon", "icon");

		addressViewTable.addText("name", "name");
		addressViewTable.addText("organisation", "organisation");
		addressViewTable.addText("street", "street");
		addressViewTable.addText("city", "city");
		addressViewTable.addText("dependentLocality", "Dependent locality");
		addressViewTable.addText("state", "state");
		addressViewTable.addText("postalCode", "Postal code");
		addressViewTable.addText("country", "country");
		addressViewTable.addFloat("latitude", "latitude");
		addressViewTable.addFloat("longitude", "longitude");

		userViewTable.addText("firstName", "First name");
		userViewTable.addText("firstNameTranslated", "First name translated");
		userViewTable.addText("lastName", "Last name");
		userViewTable.addText("lastNameTranslated", "Last name translated");
		userViewTable.addByteArray("profilePicture", "Profile picture");
		userViewTable.addByteArray("profilePictureLarge", "Profile picture large");
		userViewTable.addText("language", "language");
		userViewTable.addReference("organizationUnit", "Organization unit", "organizationUnitView", false);


		return model;
	}

}
