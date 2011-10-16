package org.apache.commons.ognl;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.commons.ognl.enhance.UnsupportedCompilationException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * $Id$
 * @author Luke Blanshard (blanshlu@netscape.net)
 * @author Drew Davidson (drew@ognl.org)
 */
class ASTProject
    extends SimpleNode
{

    public ASTProject( int id )
    {
        super( id );
    }

    public ASTProject( OgnlParser p, int id )
    {
        super( p, id );
    }

    protected Object getValueBody( OgnlContext context, Object source )
        throws OgnlException
    {
        Node expr = _children[0];
        List answer = new ArrayList();

        ElementsAccessor elementsAccessor = OgnlRuntime.getElementsAccessor( OgnlRuntime.getTargetClass( source ) );

        for ( Enumeration e = elementsAccessor.getElements( source ); e.hasMoreElements(); )
        {

            answer.add( expr.getValue( context, e.nextElement() ) );
        }

        return answer;
    }

    public String toGetSourceString( OgnlContext context, Object target )
    {
        throw new UnsupportedCompilationException( "Projection expressions not supported as native java yet." );
    }

    public String toSetSourceString( OgnlContext context, Object target )
    {
        throw new UnsupportedCompilationException( "Projection expressions not supported as native java yet." );
    }
    
    public <R, P> R accept( NodeVisitor<? extends R, ? super P> visitor, P data ) 
    {
        return visitor.visit( this, data );
    }
}
