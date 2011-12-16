/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.bank.service.impl.security;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.acls.model.UnloadedSidException;

public class SimpleAclImpl implements Acl {

	private static final long serialVersionUID = -328134516312149798L;

	private final ObjectIdentity objectIdentity;
	private final List<AccessControlEntry> entries = newArrayList();

	public SimpleAclImpl(ObjectIdentity objectIdentity) {
		this.objectIdentity = objectIdentity;
	}

	@Override
	public List<AccessControlEntry> getEntries() {
		return entries;
	}

	@Override
	public ObjectIdentity getObjectIdentity() {
		return objectIdentity;
	}

	@Override
	public Sid getOwner() {
		return null;
	}

	@Override
	public Acl getParentAcl() {
		return null;
	}

	@Override
	public boolean isEntriesInheriting() {
		return false;
	}

	@Override
	public boolean isGranted(List<Permission> permission, List<Sid> sids, boolean administrativeMode) throws NotFoundException, UnloadedSidException {

		AccessControlEntry firstRejection = null;

		for (Permission p : permission) {
			for (Sid sid : sids) {
				// Attempt to find exact match for this permission mask and SID
				boolean scanNextSid = true;

				for (AccessControlEntry ace : entries) {

					if ((ace.getPermission().getMask() == p.getMask()) && ace.getSid().equals(sid)) {
						// Found a matching ACE, so its authorization decision
						// will prevail
						if (ace.isGranting()) {
							return true;
						}

						// Failure for this permission, so stop search
						// We will see if they have a different permission
						// (this permission is 100% rejected for this SID)
						if (firstRejection == null) {
							// Store first rejection for auditing reasons
							firstRejection = ace;
						}

						scanNextSid = false; // helps break the loop

						break; // exit aces loop
					}
				}

				if (!scanNextSid) {
					break; // exit SID for loop (now try next permission)
				}
			}
		}

		if (firstRejection != null) {
			// We found an ACE to reject the request at this point, as no
			// other ACEs were found that granted a different permission
			return false;
		}

		// No matches have been found
		throw new NotFoundException("Unable to locate a matching ACE for passed permissions and SIDs");
	}

	@Override
	public boolean isSidLoaded(List<Sid> sids) {
		return true;
	}
}
