# Deployment Guide for Wellness Marketplace

## Backend Deployment to Render

### Prerequisites:
- Render account (sign up at render.com)
- GitHub account connected to Render

### Steps:

1. **Login to Render** and go to your Dashboard
2. Click **"New +"** and select **"Web Service"**
3. Connect your GitHub account and select the repository: `WellnessMarketplace_backend`
4. Select the branch: `sakshi-nimje`
5. Configure the web service:
   - **Name**: wellness-marketplace-backend
   - **Environment**: **Docker** (select Docker from the dropdown)
   - **Build Command**: Leave empty (Dockerfile handles this)
   - **Start Command**: Leave empty (Dockerfile handles this)
6. Click **"Create Web Service"**
7. Wait for the build to complete - Render will automatically deploy

### Required Environment Variables:
- `DB_URL` - Your database connection string
- `DB_USERNAME` - Database username  
- `DB_PASSWORD` - Database password
- `JWT_SECRET` - Your JWT secret key

---

## Frontend Deployment to Netlify

### Prerequisites:
- Netlify account (sign up at netlify.com)

### Steps:

1. **Login to Netlify** and go to your Dashboard
2. Click **"Add new site"** → **"Import an existing project"**
3. Connect your GitHub account and select the repository: `WellnessMarketplace_frontend`
4. Select the branch: `sakshi-nimje`
5. Configure the build settings:
   - **Build command**: `npm run build`
   - **Publish directory**: `build`
6. Click **"Deploy Site"**

### Required Environment Variables:
- `REACT_APP_API_URL` - Your Render backend URL (e.g., `https://wellness-marketplace-backend.onrender.com`)

---

## Connecting Frontend to Backend

After deployment:
1. Copy your Netlify frontend URL
2. Update your backend's CORS configuration to allow the Netlify URL
3. Update your frontend's API base URL to point to your Render backend
