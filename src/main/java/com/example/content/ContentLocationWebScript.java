package com.example.content;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.PermissionService;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class ContentLocationWebScript extends DeclarativeWebScript {

	private NodeService nodeService;
	private PermissionService permissionService;
	
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		String nodeId = req.getParameter("nodeid");
		
		if (null == nodeId || "".equals(nodeId)) {
			status.setCode(Status.STATUS_BAD_REQUEST);
			status.setRedirect(true);
			return null;
		}
		NodeRef contentNodeRef = new NodeRef("workspace://SpacesStore/" + nodeId);
		
		if (!nodeService.exists(contentNodeRef)) {
			status.setCode(Status.STATUS_NOT_FOUND);
			status.setRedirect(true);
			return null;
		}
		
		if (permissionService.hasPermission(contentNodeRef, PermissionService.READ) != AccessStatus.ALLOWED) {
			status.setCode(Status.STATUS_FORBIDDEN);
			status.setRedirect(true);
			return null;
		}
		
		ContentData contentData = (ContentData) nodeService.getProperty(contentNodeRef, ContentModel.PROP_CONTENT);
		String contentFileLocation = contentData.getContentUrl();
		Long contentSize = contentData.getSize();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("contentFSLocation", contentFileLocation);
		model.put("contentSize", contentSize);
		return model;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
